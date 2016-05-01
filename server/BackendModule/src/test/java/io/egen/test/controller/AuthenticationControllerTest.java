package io.egen.test.controller;

import java.util.Date;

import io.egen.controller.AuthenticationController;
import io.egen.controller.HomeController;
import io.egen.entity.UserDetails;
import io.egen.service.HomeService;
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
public class AuthenticationControllerTest {
	@Mock
	UserService homeservice;
	
	@InjectMocks
	AuthenticationController controller;
	
	private MockMvc mockMvc;
    private UserDetails user;
	
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
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}
	@Test
	public void testFindAllShows() throws Exception{
	
		mockMvc.perform(MockMvcRequestBuilders.post("/auth/login/"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(homeservice).login(user.getEmailId(),user.getPassword());
	}
}
