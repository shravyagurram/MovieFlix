package io.egen.service;

import io.egen.dao.HomeRepository;
import io.egen.entity.ShowDetails;


import io.egen.exception.DataNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeRepository repository;
	
	@Override
	public List<ShowDetails> findAllShows() {
		return repository.findAllShows();
		 
	}

	@Override
	public List<ShowDetails> findFilteredShows(String filterId) throws DataNotFoundException {
		
		List<ShowDetails> list= repository.findFilteredShows(filterId);
		if(list == null) {
		   throw new DataNotFoundException();
			
		}
		return list;
	}

	@Override
	public List<ShowDetails> findSortedShows(String sortId) throws DataNotFoundException {
		List<ShowDetails> list=repository.findSortedShows(sortId);
		if(list == null) {
			throw new DataNotFoundException();
		}
		return list;
	}

	@Override
	public List<ShowDetails> findTopRatedShows(String sortId) throws DataNotFoundException {
		List<ShowDetails> list=repository.findTopRatedShows(sortId);
		if(list == null) {
			throw new DataNotFoundException();
		}
		return list;
	}

	
}
