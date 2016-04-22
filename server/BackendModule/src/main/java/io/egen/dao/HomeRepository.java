package io.egen.dao;

import io.egen.entity.ShowDetails;

import java.util.List;

public interface HomeRepository {
	List<ShowDetails> findAllShows ();
	List<ShowDetails> findFilteredShows(String filterId);
	public List<ShowDetails> findSortedShows(String sortId);
	public List<ShowDetails> findTopRatedShows(String sortId);
}
