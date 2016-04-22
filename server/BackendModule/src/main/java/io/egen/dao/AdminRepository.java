package io.egen.dao;

import io.egen.entity.ShowDetails;

public interface AdminRepository {

	public ShowDetails createMovieDetails(ShowDetails details);

	public ShowDetails findByImdbId(String imdbID);
	public ShowDetails findById(String movieId);
	public ShowDetails update(ShowDetails details);

	public void delete(ShowDetails existing);
}
