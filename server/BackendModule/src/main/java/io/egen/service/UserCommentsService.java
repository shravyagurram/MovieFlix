package io.egen.service;

import io.egen.entity.UserComments;

import java.util.List;

public interface UserCommentsService {

	public UserComments logRating(UserComments userComments);

	public double viewRatings(String movieId);

	public UserComments logComment(UserComments userComments) ;

	public List<String> viewComments(String movieId);
}
