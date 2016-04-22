package io.egen.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public UserDetails findByEmail(String emailId) {
		/*//TypedQuery<UserDetails> query = em.createQuery("SELECT u from UserDetails u where u.emailId=:emailid ",UserDetails.class);
		query.setParameter("emailid", emailId);
		List<UserDetails> userslist= query.getResultList();
		if(userslist != null && userslist.size() == 1) {
			System.out.println(userslist.get(0));
			return userslist.get(0);
		}
		else {
			return null;
		}*/
		return em.find(UserDetails.class, emailId);
	}

	
	

	
}
