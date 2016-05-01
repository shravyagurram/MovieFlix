package io.egen.service;

import io.egen.entity.ShowDetails;
import io.egen.exception.DataNotFoundException;
import io.egen.exception.MovieDetailsExistsException;



public interface AdminService {

	public ShowDetails createMovieDetails (ShowDetails details) throws MovieDetailsExistsException ;

	public ShowDetails update(String movieId, ShowDetails details) throws DataNotFoundException;

	public void delete(String imdbId) throws DataNotFoundException;
}
