package io.egen.dao;

import io.egen.entity.UserComments;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class UserCommentsRepositoryImpl implements UserCommentsRepository{
	

	@PersistenceContext
	private EntityManager em;
	
	//UserComments userComments;
	
	@Override
	public UserComments logRating(UserComments userComments) {
		 em.persist(userComments);
		 return userComments;
	}

	@Override
	public double viewRatings(String movieId) {
		TypedQuery<Double> query= em.createQuery("SELECT AVG(u.userRating) FROM ShowDetails s join s.usercomments u where s.MovieId=:movieid",Double.class);
		query.setParameter("movieid", movieId);	
		double ratings=query.getSingleResult();
		return ratings;
	}

	@Override
	public UserComments logComment(UserComments userComments) {
		em.persist(userComments);
		 return userComments;
	}

	@Override
	public List<String> viewComments(String movieId) {
		TypedQuery<String> query = em.createQuery("SELECT u.userComment FROM ShowDetails s join s.usercomments u where s.MovieId=:movieid ", String.class);
		query.setParameter("movieid", movieId);
		List<String> list=query.getResultList();
		list.removeAll(Collections.singleton(null));  
		return list;
		
	}
}
