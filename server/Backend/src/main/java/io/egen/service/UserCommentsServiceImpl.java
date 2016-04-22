package io.egen.service;

import io.egen.dao.UserCommentsRepository;
import io.egen.entity.UserComments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCommentsServiceImpl implements UserCommentsService{
	
	@Autowired
	UserCommentsRepository repository;
	
	@Override
	public UserComments logRating(UserComments userComments) {
		
		return repository.logRating(userComments);
	}

	@Override
	public double viewRatings(String movieId) {
		
		return repository.viewRatings(movieId);
	}

	
	@Override
	public UserComments logComment(UserComments userComments)  {
		/*UserDetails existing = repository.findByEmail(userComments.getId());
		if(existing != null) {
			throw new UserNotFoundException();
		}*/
		return repository.logComment(userComments);
		
	}

	@Override
	public List<String> viewComments(String movieId) {
		
		return repository.viewComments(movieId);
	}

}
