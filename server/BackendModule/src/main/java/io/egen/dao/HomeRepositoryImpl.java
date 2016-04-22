package io.egen.dao;

import io.egen.entity.ShowDetails;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class HomeRepositoryImpl implements HomeRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ShowDetails> findAllShows() {
		TypedQuery<ShowDetails> query = em.createQuery("SELECT m FROM ShowDetails m" , ShowDetails.class);
		 
		List<ShowDetails> list=	query.getResultList();
		return list;
		
	}

	@Override
	public List<ShowDetails> findFilteredShows(String filterId) {
		TypedQuery<ShowDetails> query = em.createQuery("select m from ShowDetails m where  concat(Genre,Type,Year,Title) LIKE :filterid" , ShowDetails.class);
		query.setParameter("filterid", "%"+filterId+"%");
		return query.getResultList();
	}

	@Override
	public List<ShowDetails> findSortedShows(String sortId) {
		String query="select m from ShowDetails m order by "+sortId;
		TypedQuery<ShowDetails> tquery = em.createQuery(query, ShowDetails.class);
		//query.setParameter("sortid", sortId);
		return tquery.getResultList();
		
	}

	@Override
	public List<ShowDetails> findTopRatedShows(String type) {
	
		TypedQuery<ShowDetails> query = em.createQuery("select m from ShowDetails m where Type=:type and m.imdbRating>8 order by m.imdbRating", ShowDetails.class);
		query.setParameter("type", type);
		return query.getResultList();
		
	}

	
}
