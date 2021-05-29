package sbnz.app.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.dto.TripPlanDTO;
import sbnz.app.dto.TripPlanRequestDTO;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.model.Feature;
import sbnz.app.model.Location;
import sbnz.app.model.LocationType;
import sbnz.app.model.Rating;
import sbnz.app.model.TimeSpentAtLocation;
import sbnz.app.model.TripPlanRequest;
import sbnz.app.model.User;
import sbnz.app.model.UserRole;
import sbnz.app.repository.LocationRepository;
import sbnz.app.repository.LocationTypeRepository;
import sbnz.app.repository.RatingRepository;
import sbnz.app.repository.TimeSpentAtLocationRepository;
import sbnz.app.repository.UserRepository;
import sbnz.app.service.KieSessionService;
import sbnz.app.service.LocationService;
import sbnz.app.service.TripPlanService;

@Service
public class TripPlanServiceImpl implements TripPlanService {

	private UserRepository userRepository;
	private LocationRepository locationRepository;
	private LocationTypeRepository typeRepository;
	private RatingRepository ratingRepository;
	private KieSessionService sessionService;
	private LocationService locationService;
	private TimeSpentAtLocationRepository timeRepository;
	
	@Autowired
	public TripPlanServiceImpl(UserRepository userRepository, LocationRepository locationRepository,
			LocationTypeRepository typeRepository, RatingRepository ratingRepository, KieSessionService sessionService,
			LocationService locationService, TimeSpentAtLocationRepository timeRepository) {
		super();
		this.userRepository = userRepository;
		this.locationRepository = locationRepository;
		this.typeRepository = typeRepository;
		this.ratingRepository = ratingRepository;
		this.sessionService = sessionService;
		this.locationService = locationService;
		this.timeRepository = timeRepository;
	}

	@Override
	public TripPlanDTO planTrip(TripPlanRequestDTO request, User user) throws LocationTypeNotFoundException {
		KieSession session = sessionService.getRecommendationSession();
		
		insertData(session);
		FactHandle req = session.insert(toRequest(request, user));
		Map<Location, Double> recommendedLocations = new HashMap<Location, Double>();
		session.setGlobal("recommendedLocations", recommendedLocations);
		
		session.fireAllRules();
		
		session.delete(req);
		session.dispose();
		return toPlanDTO(recommendedLocations.keySet().stream().collect(Collectors.toList()), request, user.getId());
	}

	private TripPlanRequest toRequest(TripPlanRequestDTO dto, User user) throws LocationTypeNotFoundException {
		Set<LocationType> types = new HashSet<LocationType>();
		for(long typeId : dto.getTypeIds()) {
			LocationType type = typeRepository.findById(typeId).orElse(null);
			if (type == null)
				throw new LocationTypeNotFoundException();
			types.add(type);
		}
		return new TripPlanRequest(user, types, dto.getDurationInDays());
	}
	
	private TripPlanDTO toPlanDTO(List<Location> locations, TripPlanRequestDTO request, long userId) {
		return new TripPlanDTO(request, locationService.toUserView(locations, userId));
	}
	
	private void insertData(KieSession session) {
		for(User u : userRepository.findAll().stream().filter(u -> u.getRole() == UserRole.BASIC_USER).collect(Collectors.toList()))
			session.insert(u);
		for(Location l : locationRepository.findAll())
			session.insert(l);
		for(LocationType lt : typeRepository.findAll())
			session.insert(lt);
		for(Rating r : ratingRepository.findAll())
			session.insert(r);
		for(Feature f : Feature.values())
			session.insert(f);
		for(TimeSpentAtLocation t : timeRepository.findAll())
			session.insert(t);
	}
}
