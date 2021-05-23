package sbnz.app.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.model.Feature;
import sbnz.app.model.Location;
import sbnz.app.model.LocationType;
import sbnz.app.model.Rating;
import sbnz.app.model.RatingType;
import sbnz.app.model.TripPlanRequest;
import sbnz.app.model.User;
import sbnz.app.repository.UserRepository;

@Service
public class SampleService {

	private KieSessionService sessionService;
	private UserRepository repo;
	private PromotionService promotionService;
	
	@Autowired
	public SampleService(KieSessionService sessionService, UserRepository repo, PromotionService promotionService) {
		this.sessionService = sessionService;
		this.repo = repo;
		this.promotionService = promotionService;
	}
	
	public void test() {
		promotionService.getPromotedLocations();
		/*
		KieSession kieSession = sessionService.getRecommendationSession();
		
		List<User> users = repo.findAll();
		Set<Feature> featuresGallery = new HashSet<Feature>();
		featuresGallery.add(Feature.ART);
		Set<Feature> featuresCinema = new HashSet<Feature>(featuresGallery);
		featuresCinema.add(Feature.FOOD_AND_DRINK);
		User u = new User();
		LocationType typeGallery = new LocationType(0, "gallery", featuresGallery);
		LocationType typeCinema = new LocationType(1, "cinema", featuresCinema);
		Location locationGallery = new Location(0, typeGallery, "gallery", "", "", "");
		Location locationCinema = new Location(1, typeCinema, "cinema", "", "", "");
		Set<LocationType> types = new HashSet<LocationType>();
		types.add(typeGallery);
		TripPlanRequest plan = new TripPlanRequest(0, u, null, types, 2);
		Rating ratingCinema = new Rating(0, u, locationCinema, RatingType.LIKE);
		
		kieSession.insert(typeGallery);
		kieSession.insert(typeCinema);
		kieSession.insert(locationGallery);
		kieSession.insert(locationCinema);
		kieSession.insert(plan);
		kieSession.insert(ratingCinema);
		for(User user : users) {
			kieSession.insert(user);
			kieSession.insert(new Rating(0, user, locationCinema, RatingType.LIKE));
		}
		for(Feature f : Feature.values()) {
			kieSession.insert(f);
		}
		
		kieSession.setGlobal("recommendedLocations", new HashMap<Location, Double>());
		
		kieSession.fireAllRules();
		
		@SuppressWarnings("unchecked")
		Map<Location, Double> recommendedLocations = (Map<Location, Double>) kieSession.getGlobal("recommendedLocations");
		System.out.println("List of recommended locations: ");
		for(Location location : recommendedLocations.keySet()) {
			System.out.println(location.getName());
			System.out.println(recommendedLocations.get(location));
		}
		*/
	}
}
