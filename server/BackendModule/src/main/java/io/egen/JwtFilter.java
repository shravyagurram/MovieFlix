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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

	@Autowired
	UserService service;

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res,
			final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
        
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE,OPTIONS, HEAD");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,authorization");
       // response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	final String authHeader = request.getHeader("Authorization");
    		System.out.println("authheader "+authHeader);
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
           // chain.doFilter(req, res);
        }
		
	}

}
