package io.egen.test.controller;

import java.util.Date;

import io.egen.controller.AdminController;
import io.egen.controller.UserController;
import io.egen.entity.ShowDetails;
import io.egen.entity.UserDetails;
import io.egen.service.AdminService;
import io.egen.service.UserService;
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
public class AdminControllerTest {
	@Mock
	AdminService adminservice;
	
	@InjectMocks
	AdminController controller;
	
	private MockMvc mockMvc;
	private ShowDetails details;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		details=new ShowDetails();
		details.setMovieId("0e48ed09-a9c7-4154-a124-1ddaed19e149");
		details.setTitle("dummy movie");
		details.setYear(1994);
		details.setRated("dunnyrated");
		details.setReleased(new Date());
		details.setRuntime("140min");
		details.setGenre("comedy");
		details.setDirector("dummy dir");
		details.setActors("dummy actor");
		details.setWriter("dummy writer");
		details.setPlot("dummy plot");
		details.setLanguage("english");
		details.setCountry("USA");
		details.setAwards("dummy awards");
		details.setPoster("abc.jpg");
		details.setMetascore("50");
		details.setImdbRating(7.5);
		details.setImdbVotes(3344);
		details.setImdbID("dhfb23");
		details.setType("movie");
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}
	@Test
	public void testCreateMoviedetails() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(adminservice).createMovieDetails(details);
	}
	@Test
	public void testUpdateMoviedetails() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/" + details.getMovieId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(adminservice).update(details.getMovieId(),details);
	}
	@Test
	public void testDeleteMoviedetails() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/"+details.getImdbID()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(adminservice).delete(details.getImdbID());
	}
	
}
