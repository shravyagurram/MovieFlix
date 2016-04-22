package io.egen.test.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junit.framework.Assert;
import io.egen.dao.AdminRepository;
import io.egen.dao.AdminRepositoryImpl;
import io.egen.entity.ShowDetails;
import io.egen.entity.UserDetails;
import io.egen.service.AdminService;
import io.egen.service.AdminServiceImpl;
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
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class AdminRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	TypedQuery<ShowDetails> query ;
	
	@InjectMocks
	AdminRepository repository=new AdminRepositoryImpl();
	
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
	public void testCreateMovieDetails(){
		repository.createMovieDetails(details);
		Mockito.verify(em).persist(details);
	}
	
	@Test
	public void testFindByImdbId(){
		List<ShowDetails> expected=Arrays.asList(details);
		Mockito.when(em.createQuery(
				"select m from ShowDetails m where m.imdbID=:imdbid",ShowDetails.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		ShowDetails actual=repository.findByImdbId(details.getImdbID());
		Assert.assertEquals(details, actual);
	}
	@Test
	public void testFindByImdbIdNull(){
		Mockito.when(em.createQuery(
				"select m from ShowDetails m where m.imdbID=:imdbid",ShowDetails.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		ShowDetails actual=repository.findByImdbId(details.getImdbID());
		Assert.assertEquals(null, actual);
	}
	@Test
	public void testFindById(){
		Mockito.when(em.find(ShowDetails.class,details.getMovieId())).thenReturn(details);
		ShowDetails actual=repository.findById(details.getMovieId());
		Assert.assertEquals(details, actual);
	}
	@Test
	public void testUpdateMovieDetails(){
		repository.update(details);
		Mockito.verify(em).merge(details);
	}
	@Test
	public void testDeleteMovieDetails(){
		repository.delete(details);
		Mockito.verify(em).remove(details);
	}
	
}
