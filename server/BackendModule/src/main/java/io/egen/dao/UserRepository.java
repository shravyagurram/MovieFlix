package io.egen.dao;

import java.util.List;

import io.egen.entity.UserComments;
import io.egen.entity.UserDetails;

public interface UserRepository {

	UserDetails create(UserDetails userdetails);


	UserDetails findByEmail(String emailId);


	UserDetails findById(String id);



	

}
