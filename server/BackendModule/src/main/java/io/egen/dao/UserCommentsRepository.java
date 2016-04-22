package io.egen.dao;

import io.egen.entity.UserComments;

import java.util.List;

public interface UserCommentsRepository {


	UserComments logRating(UserComments userComments);

	double viewRatings(String movieId);

	UserComments logComment(UserComments userComments);

	List<String> viewComments(String movieId);
}
