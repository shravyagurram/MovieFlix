package io.egen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.dao.UserRepository;
import io.egen.entity.UserComments;
import io.egen.entity.UserDetails;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails create(UserDetails userdetails) throws UserAlreadyExistsException {
		UserDetails existing = repository.findByEmail(userdetails.getEmailId());
		if(existing != null) {
			throw new UserAlreadyExistsException();
		}
		return repository.create(userdetails);
		
	}

	
	@Override
	public UserDetails login(String emailId, String password) throws UserNotFoundException {
		UserDetails existing = repository.findByEmail(emailId);
		System.out.println("in service"+emailId);
		
		if(existing == null) {
			throw new UserNotFoundException();
		}
		   return existing;
		
	}

	public UserDetails findByEmail(String emailId){
		UserDetails details=repository.findByEmail(emailId);
		return details;
	}
}
