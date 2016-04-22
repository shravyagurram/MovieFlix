package io.egen.service;

import java.util.List;

import io.egen.entity.UserComments;
import io.egen.entity.UserDetails;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;

public interface UserService {

	public UserDetails create(UserDetails userdetails) throws UserAlreadyExistsException;

	public UserDetails login(String emailId, String password) throws UserNotFoundException;
    
	public UserDetails findByEmail(String emailId);
}
