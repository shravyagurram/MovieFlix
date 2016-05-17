package io.egen.service;

import io.egen.entity.ShowDetails;
import io.egen.exception.DataNotFoundException;

import java.util.List;

public interface HomeService {

	List<ShowDetails> findAllShows ();
	List<ShowDetails> findFilteredShows(String filterId) throws DataNotFoundException;
	public List<ShowDetails> findSortedShows(String sortId) throws DataNotFoundException;;
	public List<ShowDetails> findTopRatedShows(String sortId) throws DataNotFoundException;
	public ShowDetails findShowById(String id) throws DataNotFoundException;;
}
