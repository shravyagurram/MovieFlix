package io.egen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.entity.UserComments;
import io.egen.entity.UserDetails;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	UserComments userComments;

	@Override
	public UserDetails create(UserDetails userdetails) {
		em.persist(userdetails);
		return userdetails;
	}

	

	@Override
	public UserDetails findById(String emailId) {
		/*TypedQuery<UserDetails> query = em.createQuery("SELECT u from UserDetails u where u.username=:username ",UserDetails.class);
		query.setParameter("username", username);
		List<UserDetails> userslist= query.getResultList();
		if(userslist != null && userslist.size() == 1) {
			System.out.println(userslist.get(0));
			return userslist.get(0);
		}
		else {
			return null;
		}*/
	     UserDetails udetails=em.find(UserDetails.class, emailId);
		 System.out.println(udetails);
		 return udetails;
	}



	@Override
	public UserDetails findByEmail(String emailId) {
		UserDetails udetails=em.find(UserDetails.class, emailId);
			
				return udetails;
	}



	

	
	

	
}
