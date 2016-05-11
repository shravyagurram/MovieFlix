package io.egen.service;

import io.egen.dao.AdminRepository;
import io.egen.entity.ShowDetails;
import io.egen.exception.DataNotFoundException;
import io.egen.exception.MovieDetailsExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository repository;

	@Override
	public ShowDetails createMovieDetails(ShowDetails details)
			throws MovieDetailsExistsException {
		ShowDetails existing = repository.findByImdbId(details.getImdbID());
		if (existing != null) {
			throw new MovieDetailsExistsException();
		}
		return repository.createMovieDetails(details);
	}

	@Override
	public ShowDetails update(String movieId, ShowDetails details)
			throws DataNotFoundException {
		ShowDetails existing = repository.findById(movieId);
		if (existing == null) {
			throw new DataNotFoundException();
		}
		return repository.update(details);
	}

	@Override
	public void delete(String movieId) throws DataNotFoundException {

		ShowDetails existing = repository.findById(movieId);
		if (existing == null) {
			throw new DataNotFoundException();
		}
		repository.delete(existing);
	}
}
