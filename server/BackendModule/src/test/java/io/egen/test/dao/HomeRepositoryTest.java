package io.egen.test.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junit.framework.Assert;
import io.egen.dao.HomeRepository;
import io.egen.dao.HomeRepositoryImpl;
import io.egen.entity.ShowDetails;
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
public class HomeRepositoryTest {

	@Mock
	private EntityManager em;
	
	@Mock
	TypedQuery<ShowDetails> query ;
	
	@InjectMocks
	HomeRepository repository=new HomeRepositoryImpl();
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
		List<ShowDetails> expected=Arrays.asList(details);
		Mockito.when(em.createQuery("SELECT m FROM ShowDetails m" , ShowDetails.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		List<ShowDetails> actual=repository.findAllShows();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testFindFilteredShows(){
		List<ShowDetails> expected=Arrays.asList(details);
		Mockito.when(em.createQuery("select m from ShowDetails m where  concat(Genre,Type,Year,Title) LIKE :filterid" , ShowDetails.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		List<ShowDetails> actual=repository.findFilteredShows("movie");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testFindSortedShows(){
		
		List<ShowDetails> expected=Arrays.asList(details);
		Mockito.when(em.createQuery("select m from ShowDetails m order by m.Year" , ShowDetails.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		List<ShowDetails> actual=repository.findSortedShows("imdbRating");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testFindTopRatedShows(){
		List<ShowDetails> expected=Arrays.asList(details);
		Mockito.when(em.createQuery("select m from ShowDetails m where Type=:type and m.imdbRating>8 order by m.imdbRating" , ShowDetails.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		List<ShowDetails> actual=repository.findTopRatedShows(details.getType());
		Assert.assertEquals(expected, actual);
	}
	
	
}
