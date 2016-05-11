package io.egen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.egen.entity.UserComments;
import io.egen.service.UserCommentsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/usercomments")
public class UserCommentsController {

	@Autowired
	UserCommentsService commentservice;
	
	@RequestMapping(value = "/lograting", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public UserComments logRating(@RequestBody UserComments userComments) {
		System.out.println("user "+ userComments.getUserdetails());
		System.out.println("movie "+ userComments.getShowdetails().getMovieId());
		System.out.println("controller "+ userComments);
		return commentservice.logRating(userComments);
	}

	@RequestMapping(value = "/{movieid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public double viewRatings(@PathVariable("movieid") String movieId) {
		//System.out.println("user attribute" + req.getAttribute("emailid"));
		return commentservice.viewRatings(movieId);
	}

	@RequestMapping(value = "/logcomment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public UserComments logComment(@RequestBody UserComments userComments) {
		return commentservice.logComment(userComments);
	}

	@RequestMapping(value = "/{movieid}/userComment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<String> viewComments(@PathVariable("movieid") String movieId) {
		return commentservice.viewComments(movieId);
	}

}
