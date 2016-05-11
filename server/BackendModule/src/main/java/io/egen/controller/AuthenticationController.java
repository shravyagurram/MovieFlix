package io.egen.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.egen.entity.UserDetails;
import io.egen.exception.UserNotFoundException;
import io.egen.service.LoginResponse;
import io.egen.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginResponse login(@RequestBody UserDetails userdetails)
			throws UserNotFoundException {
	
		UserDetails details = service.login(userdetails.getUsername(),
				userdetails.getPassword());
		System.out.println("in controller" + details.getPassword());
		if ((details.getPassword()).equals(userdetails.getPassword())) {

			return new LoginResponse(Jwts.builder()
					.setSubject(userdetails.getUsername())
					.claim("roles", details.getUserType())
					.setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		}
		throw new UserNotFoundException();
	}
}
