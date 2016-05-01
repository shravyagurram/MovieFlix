package io.ege.test.service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import io.egen.dao.UserCommentsRepository;
import io.egen.dao.UserCommentsRepositoryImpl;
import io.egen.entity.ShowDetails;
import io.egen.entity.UserComments;
import io.egen.entity.UserDetails;
import io.egen.service.HomeService;
import io.egen.service.HomeServiceImpl;
import io.egen.service.UserCommentsService;
import io.egen.service.UserCommentsServiceImpl;
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
public class UserCommentsServiceTest {
	@Mock
	private EntityManager em;
	
	@Mock
	TypedQuery<UserComments> query ;
	
	@Mock
	TypedQuery<Double> tquery; 
	
	@Mock
	TypedQuery<String> squery;
	
	@Mock
	UserCommentsRepository repository=new UserCommentsRepositoryImpl();
	
	@InjectMocks
	UserCommentsService service=new UserCommentsServiceImpl();
	
	private UserComments comments;
	private ShowDetails details;
	private UserDetails user;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
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
	}
	@Test
	public void testLogRating(){
		service.logRating(comments);
		Mockito.verify(repository).logRating(comments);
	}
	
	@Test
	public void testViewRatings(){
		service.viewRatings(comments.getShowdetails().getMovieId());
		Mockito.verify(repository).viewRatings(comments.getShowdetails().getMovieId());
	}
	
	@Test
	public void testLogComment(){
		service.logComment(comments);
		Mockito.verify(repository).logComment(comments);
	}
	
	@Test
	public void testViewComments(){
		service.viewComments(comments.getShowdetails().getMovieId());
		Mockito.verify(repository).viewComments(comments.getShowdetails().getMovieId());
	}
}
