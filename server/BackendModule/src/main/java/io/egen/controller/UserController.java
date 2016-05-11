package io.egen.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.egen.entity.ShowDetails;
import io.egen.entity.UserComments;
import io.egen.entity.UserDetails;
import io.egen.exception.DataNotFoundException;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;
import io.egen.service.LoginResponse;
import io.egen.service.UserCommentsService;
import io.egen.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
@Api(tags = "User")
public class UserController {

	@Autowired
	UserService service;
	
	

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "create user", notes = "creates users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public UserDetails create(@RequestBody UserDetails userdetails)
			throws UserAlreadyExistsException {
		return service.create(userdetails);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserDetails findUserById(@PathVariable("username") String Id) throws UserNotFoundException{
		
		 return service.findUserById(Id);
	}

	
}
