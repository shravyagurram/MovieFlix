package io.ege.test.service;

import java.util.Date;

import junit.framework.Assert;
import io.egen.dao.UserRepository;
import io.egen.entity.UserDetails;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;
import io.egen.service.UserService;
import io.egen.service.UserServiceImpl;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class UserServiceTest {

	@Mock
	UserRepository repository;
	
	@InjectMocks
	UserService service=new UserServiceImpl();
	
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
	}
	
	@Test
	public void testFindByEmail(){
		service.findByEmail(user.getEmailId());
		Mockito.verify(repository).findByEmail(user.getEmailId());
	}
	
	@Test(expected=UserAlreadyExistsException.class)
	public void testCreateUserException() throws UserAlreadyExistsException{
		Mockito.when(repository.findByEmail(user.getEmailId())).thenReturn(user);
		service.create(user);
	}
	
	@Test
	public void testCreateUser() throws UserAlreadyExistsException{
		Mockito.when(repository.findByEmail(user.getEmailId())).thenReturn(null);
		UserDetails actual=service.create(user);
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testLogin() throws UserNotFoundException{
		Mockito.when(repository.findByEmail(user.getEmailId())).thenReturn(user);
		UserDetails actual=service.login(user.getEmailId(),user.getPassword());
		Assert.assertEquals(user, actual);
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testLoginException() throws UserNotFoundException{
		Mockito.when(repository.findByEmail(user.getEmailId())).thenReturn(null);
		service.login(user.getEmailId(),user.getPassword());
		
	}
	
}
