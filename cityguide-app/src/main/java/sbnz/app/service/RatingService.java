package sbnz.app.service;

import sbnz.app.dto.NewRatingDTO;
import sbnz.app.exception.LocationNotFoundException;
import sbnz.app.model.User;

public interface RatingService {

	public void addRating(NewRatingDTO newRating, User user) throws LocationNotFoundException;
}
