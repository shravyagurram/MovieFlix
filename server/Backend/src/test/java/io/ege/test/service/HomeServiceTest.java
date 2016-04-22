package io.ege.test.service;

import java.util.Date;

import junit.framework.Assert;
import io.egen.dao.HomeRepository;
import io.egen.entity.ShowDetails;
import io.egen.entity.UserDetails;
import io.egen.exception.DataNotFoundException;
import io.egen.exception.UserNotFoundException;
import io.egen.service.HomeService;
import io.egen.service.HomeServiceImpl;
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
public class HomeServiceTest {
	
	@Mock
	HomeRepository repository;
	
	@InjectMocks
	HomeService service=new HomeServiceImpl();
	
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
	
	@Test
	public void testFindAllShows(){
		service.findAllShows();
		Mockito.verify(repository).findAllShows();
	}
	
	@Test
	public void testfindFilteredShows() throws DataNotFoundException{
		service.findFilteredShows(details.getType());
		Mockito.verify(repository).findFilteredShows(details.getType());
	}
	
	@Test(expected=DataNotFoundException.class)
	public void testfindFilteredShowsException() throws DataNotFoundException{
		Mockito.when(repository.findFilteredShows(details.getType())).thenReturn(null);
		service.findFilteredShows(details.getType());
		
	}
	
	@Test
	public void testfindSortedShows() throws DataNotFoundException{
		service.findSortedShows(details.getType());
		Mockito.verify(repository).findSortedShows(details.getType());
	}
	
	@Test(expected=DataNotFoundException.class)
	public void testfindSortedShowsException() throws DataNotFoundException{
		Mockito.when(repository.findSortedShows(details.getType())).thenReturn(null);
		service.findSortedShows(details.getType());
		
	}
	
	@Test
	public void testFindTopRatedShows() throws DataNotFoundException{
		service.findTopRatedShows(details.getType());
		Mockito.verify(repository).findTopRatedShows(details.getType());
	}
	
	@Test(expected=DataNotFoundException.class)
	public void testfindTopRatedShowsException() throws DataNotFoundException{
		Mockito.when(repository.findTopRatedShows(details.getType())).thenReturn(null);
		service.findTopRatedShows(details.getType());
		
	}
	
}
