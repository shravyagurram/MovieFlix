package io.egen;

import io.egen.entity.UserDetails;
import io.egen.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

	@Autowired
	UserService service;

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res,
			final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;

		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new ServletException(
					"Missing or invalid Authorization header.");
		}

		final String token = authHeader.substring(7);
		System.out.println(token);

		try {
			String EmailId = Jwts.parser().setSigningKey("secretkey")
					.parseClaimsJws(token).getBody().getSubject();
			System.out.println(EmailId);
			// UserDetails userdetails=service.findByEmail(EmailId);
			request.setAttribute("emailid", EmailId);
		} catch (final SignatureException e) {
			throw new ServletException("Invalid token.");
		}

		chain.doFilter(req, res);
	}

}
