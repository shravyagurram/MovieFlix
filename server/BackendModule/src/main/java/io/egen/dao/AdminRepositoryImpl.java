package io.egen.dao;

import java.util.List;

import io.egen.entity.ShowDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public ShowDetails createMovieDetails(ShowDetails details) {
		em.persist(details);
		return details;
	}

	@Override
	public ShowDetails findByImdbId(String imdbid) {
		TypedQuery<ShowDetails> query = em.createQuery(
				"select m from ShowDetails m where m.imdbID=:imdbid",
				ShowDetails.class);
		query.setParameter("imdbid", imdbid);
		List<ShowDetails> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);
		} else {
			return null;
		}

	}
	public ShowDetails findById(String MovieId) {
		/*TypedQuery<ShowDetails> query = em.createQuery(
				"select m from ShowDetails m where m.MovieId=:movieid",
				ShowDetails.class);
		query.setParameter("movieid", movieId);
		List<ShowDetails> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);
		} else {
			return null;
		}*/
		return em.find(ShowDetails.class, MovieId);
	}

	@Override
	public ShowDetails update(ShowDetails details) {
		return em.merge(details);

	}

	@Override
	public void delete(ShowDetails details) {
		em.remove(details);

	}

}
