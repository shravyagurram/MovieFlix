package io.egen.test.controller;

import java.util.Date;

import io.egen.controller.UserCommentsController;
import io.egen.entity.ShowDetails;
import io.egen.entity.UserComments;
import io.egen.entity.UserDetails;
import io.egen.service.UserCommentsService;
import io.egen.test.TestConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
@WebAppConfiguration
public class UserCommentsControllerTest {
	@Mock
	UserCommentsService service;
	
	@InjectMocks
    UserCommentsController controller;
	
	private MockMvc mockMvc;
	private UserDetails user;
	private ShowDetails details;
	private UserComments comments;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		user=new UserDetails();
		user.setEmailId("dummy@dummy.com");
		user.setFirstName("dummy");
		user.setLastName("user");
		user.setUserType("user");
		user.setPassword("abc12");
		user.setCreatedDate(new Date(2016-13-04));
		comments=new UserComments();
		comments.setId(5);
		comments.setUserComment("good");
		comments.setUserRating(8);
		details=new ShowDetails();
		user= new UserDetails();
		details.setMovieId("0e48ed09-a9c7-4154-a124-1ddaed19e149");
		comments.setShowdetails(details);
	    user.setEmailId("dummy@gmail.com");
		comments.setUserdetails(user);	
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}
	@Test
	public void testLograting() throws Exception{
		System.out.println("movieid"+comments.getShowdetails().getMovieId());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/usercomments/"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).logRating(comments);
	}
	@Test
	public void testViewrating() throws Exception{
		System.out.println("movieid"+comments.getShowdetails().getMovieId());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/usercomments/"+ comments.getShowdetails().getMovieId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).viewRatings(comments.getShowdetails().getMovieId());
	}
	@Test
	public void testComment() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/usercomments/"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).logComment(comments);
	}
	@Test
	public void testViewComments() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/usercomments/"+ comments.getShowdetails().getMovieId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).viewComments(comments.getShowdetails().getMovieId());
	}
}
