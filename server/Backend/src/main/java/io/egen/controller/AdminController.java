package io.egen.controller;

import io.egen.entity.ShowDetails;

import io.egen.exception.DataNotFoundException;
import io.egen.exception.MovieDetailsExistsException;
import io.egen.service.AdminService;
import io.swagger.annotations.Api;
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
@RequestMapping(value = "/api/admin")
@Api(tags = "Admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ShowDetails createMovieDetails(@RequestBody ShowDetails details)
			throws MovieDetailsExistsException {
		return service.createMovieDetails(details);
	}

	@RequestMapping(value = "/{movieid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ShowDetails updateMovieDetails(
			@PathVariable("movieid") String movieId,
			@RequestBody ShowDetails details) throws DataNotFoundException {
		return service.update(movieId, details);
	}

	@RequestMapping(value = "/{imdbid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteMovieDetails(@PathVariable("imdbid") String imdbId)
			throws DataNotFoundException {
		service.delete(imdbId);
	}
}
