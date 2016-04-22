package io.egen.controller;

import io.egen.entity.ShowDetails;

import io.egen.exception.DataNotFoundException;
import io.egen.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/home")
@Api(tags = "HomePage")
public class HomeController {

	@Autowired
	HomeService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Movie details", notes = "returns list of movie details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<ShowDetails> findAllShows() {
		return service.findAllShows();
	}

	@RequestMapping(value = "/filter/{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Filter Movies", notes = "Filter movies using movie name,year,type and genre")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<ShowDetails> findFilteredShows(
			@PathVariable("value") String filterId)
			throws DataNotFoundException {
		return service.findFilteredShows(filterId);
	}

	@RequestMapping(value = "/sort/{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "sort movies", notes = "sort movies according to imdb rating,votes,year")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<ShowDetails> findSortedShows(
			@PathVariable("value") String sortId) throws DataNotFoundException {
		return service.findSortedShows(sortId);
	}

	@RequestMapping(value = "/toprated/{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Toprated movies", notes = "Displays movies in sorted order having imdb rating more than 8 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<ShowDetails> findTopRatedShows(
			@PathVariable("value") String type) throws DataNotFoundException {
		return service.findTopRatedShows(type);
	}

}
