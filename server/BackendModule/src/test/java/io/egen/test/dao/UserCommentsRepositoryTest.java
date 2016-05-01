package io.egen.test.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junit.framework.Assert;
import io.egen.dao.AdminRepository;
import io.egen.dao.AdminRepositoryImpl;
import io.egen.dao.UserCommentsRepository;
import io.egen.dao.UserCommentsRepositoryImpl;
import io.egen.entity.ShowDetails;
import io.egen.entity.UserComments;
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
public class UserCommentsRepositoryTest {

	@Mock
	private EntityManager em;
	
	@Mock
	TypedQuery<UserComments> query ;
	
	@Mock
	TypedQuery<Double> tquery; 
	
	@Mock
	TypedQuery<String> squery;
	
	@InjectMocks
	UserCommentsRepository repository=new UserCommentsRepositoryImpl();
	
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
		repository.logRating(comments);
		Mockito.verify(em).persist(comments);
	}
	@Test
	public void testLogComments(){
		repository.logComment(comments);
		Mockito.verify(em).persist(comments);
	}
	@Test
	public void testViewRating(){
	    Mockito.when(em.createQuery("SELECT AVG(u.userRating) FROM ShowDetails s join s.usercomments u where s.MovieId=:movieid",Double.class)).thenReturn(tquery);
	   // Mockito.when(query.getSingleResult()).thenReturn(comments.getUserRating());
	      repository.viewRatings(comments.getShowdetails().getMovieId());
	   // Assert.assertEquals(comments.getUserRating(), Rating);
	}
	@Test
	public void testViewComments(){
		List<String> expected=Arrays.asList(toString());
		Mockito.when(em.createQuery("SELECT u.userComment FROM ShowDetails s join s.usercomments u where s.MovieId=:movieid ", String.class)).thenReturn(squery);
		Mockito.when(query.getSingleResult()).thenReturn(comments);
		System.out.println(comments.getShowdetails().getMovieId());
		List<String> list=repository.viewComments(comments.getShowdetails().getMovieId());
		System.out.println(list);
		 Assert.assertEquals(expected, list);
	}
	
}
