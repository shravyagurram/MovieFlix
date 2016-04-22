package io.egen.test.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junit.framework.Assert;
import io.egen.dao.UserRepository;
import io.egen.dao.UserRepositoryImpl;
import io.egen.entity.UserDetails;
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
public class UserRepositoryTest {

	@Mock
	private EntityManager em;
	
	@Mock
	TypedQuery<UserDetails> query ;
	
	private UserDetails user;
	
	
	@InjectMocks
	private UserRepository repository= new UserRepositoryImpl();
	
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
	public void testCreateUser(){
		repository.create(user);
		Mockito.verify(em).persist(user);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFindByEmail(){
		Mockito.when(em.find(UserDetails.class,user.getEmailId())).thenReturn(user);
		UserDetails actual=repository.findByEmail(user.getEmailId());
		System.out.println(actual.getEmailId());
		Assert.assertEquals(user, actual);
	}
	
}
