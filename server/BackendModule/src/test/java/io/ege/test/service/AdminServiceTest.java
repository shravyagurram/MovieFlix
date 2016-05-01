package io.ege.test.service;

import java.util.Date;

import junit.framework.Assert;
import io.egen.dao.AdminRepository;
import io.egen.entity.ShowDetails;
import io.egen.entity.UserDetails;
import io.egen.exception.DataNotFoundException;
import io.egen.exception.MovieDetailsExistsException;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.service.AdminService;
import io.egen.service.AdminServiceImpl;
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
public class AdminServiceTest {

	@Mock
	AdminRepository repository;
	
	@InjectMocks
	AdminService service=new AdminServiceImpl();
	
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
	}
	
	@Test(expected=MovieDetailsExistsException.class)
	public void testCreateMovieDetailsException() throws MovieDetailsExistsException{
		Mockito.when(repository.findByImdbId(details.getImdbID())).thenReturn(details);
		service.createMovieDetails(details);
	}
	
	@Test
	public void testCreateMovieDetails() throws MovieDetailsExistsException{
		Mockito.when(repository.findByImdbId(details.getImdbID())).thenReturn(null);
		ShowDetails actual=service.createMovieDetails(details);
		Assert.assertEquals(null, actual);
	}
	
	@Test(expected=DataNotFoundException.class)
	public void testUpdateMovieDetailsException() throws DataNotFoundException{
		Mockito.when(repository.findById(details.getMovieId())).thenReturn(null);
		service.update(details.getMovieId(),details);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateMovieDetails() throws DataNotFoundException  {
	     service.update(details.getMovieId(),details);
	     Mockito.verify(repository).update(details);
	}
	
	@Test(expected=DataNotFoundException.class)
	public void testDeleteMovieDetailsException() throws DataNotFoundException{
		Mockito.when(repository.findByImdbId(details.getImdbID())).thenReturn(null);
		service.delete(details.getImdbID());;
		
	}
	
	@Test
	public void testDeleteMovieDetails() throws DataNotFoundException {
		 service.delete(details.getMovieId());
		Mockito.verify(repository).delete(details);
	}
}
