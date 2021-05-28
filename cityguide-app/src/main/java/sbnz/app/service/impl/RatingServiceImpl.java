package sbnz.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.dto.NewRatingDTO;
import sbnz.app.exception.LocationNotFoundException;
import sbnz.app.fact.event.RatingCreated;
import sbnz.app.model.Location;
import sbnz.app.model.Rating;
import sbnz.app.model.RatingType;
import sbnz.app.model.User;
import sbnz.app.repository.LocationRepository;
import sbnz.app.repository.RatingCreatedRepository;
import sbnz.app.repository.RatingRepository;
import sbnz.app.service.KieSessionService;
import sbnz.app.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	private RatingRepository ratingRepository;
	private LocationRepository locationRepository;
	private RatingCreatedRepository ratingCreatedRepository;
	private KieSessionService sessionService;
	
	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository, LocationRepository locationRepository,
			RatingCreatedRepository ratingCreatedRepository, KieSessionService sessionService) {
		super();
		this.ratingRepository = ratingRepository;
		this.locationRepository = locationRepository;
		this.ratingCreatedRepository = ratingCreatedRepository;
		this.sessionService = sessionService;
	}

	@Override
	public void addRating(NewRatingDTO newRating, User user) throws LocationNotFoundException {
		Location l = locationRepository.findById(newRating.getLocationId()).orElse(null);
		if(l == null)
			throw new LocationNotFoundException();
		Rating rating = ratingRepository.findAll().stream().filter(r -> r.getUser().getId() == user.getId() && r.getLocation().getId() == newRating.getLocationId())
				.findAny().orElse(null);
		if(rating == null) {
			rating = new Rating(user, l, newRating.isLike() ? RatingType.LIKE : RatingType.DISLIKE);
		} else {
			rating.setType(newRating.isLike() ? RatingType.LIKE : RatingType.DISLIKE);
		}
		rating = ratingRepository.save(rating);
		RatingCreated rc = new RatingCreated(l, new Date(), rating.getType());
		rc = ratingCreatedRepository.save(rc);
		sessionService.getPromotionSession().insert(rc);
	}

}
