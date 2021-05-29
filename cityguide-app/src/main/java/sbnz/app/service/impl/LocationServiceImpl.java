package sbnz.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.dto.LocationAdminViewDTO;
import sbnz.app.dto.LocationPageVisitedDTO;
import sbnz.app.dto.LocationUserViewDTO;
import sbnz.app.dto.NewLocationDTO;
import sbnz.app.exception.LocationNotFoundException;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.fact.event.LocationCreated;
import sbnz.app.fact.event.LocationDeleted;
import sbnz.app.fact.event.LocationInfoPageVisited;
import sbnz.app.model.Location;
import sbnz.app.model.LocationType;
import sbnz.app.model.RatingType;
import sbnz.app.model.TimeSpentAtLocation;
import sbnz.app.repository.BadDayRepository;
import sbnz.app.repository.LocationCreatedRepository;
import sbnz.app.repository.LocationInfoPageVisitedRepository;
import sbnz.app.repository.LocationRepository;
import sbnz.app.repository.LocationTypeRepository;
import sbnz.app.repository.PromotionRepository;
import sbnz.app.repository.RatingCreatedRepository;
import sbnz.app.repository.RatingRepository;
import sbnz.app.repository.TimeSpentAtLocationRepository;
import sbnz.app.service.KieSessionService;
import sbnz.app.service.LocationService;
import sbnz.app.service.PromotionService;

@Service
public class LocationServiceImpl implements LocationService {

	private LocationRepository locationRepository;
	private LocationTypeRepository typeRepository;
	private LocationCreatedRepository locationCreatedRepository;
	private RatingRepository ratingRepository;
	private TimeSpentAtLocationRepository timeRepository;
	private LocationInfoPageVisitedRepository visitRepository;
	private PromotionService promotionService;
	private RatingCreatedRepository ratingCreatedRepository;
	private PromotionRepository promotionRepository;
	private BadDayRepository badDayRepository;
	private KieSessionService sessionService;
	
	@Autowired
	public LocationServiceImpl(LocationRepository locationRepository, LocationTypeRepository typeRepository,
			LocationCreatedRepository locationCreatedRepository, RatingRepository ratingRepository,
			TimeSpentAtLocationRepository timeRepository, LocationInfoPageVisitedRepository visitRepository,
			PromotionService promotionService, RatingCreatedRepository ratingCreatedRepository,
			PromotionRepository promotionRepository, BadDayRepository badDayRepository,
			KieSessionService sessionService) {
		super();
		this.locationRepository = locationRepository;
		this.typeRepository = typeRepository;
		this.locationCreatedRepository = locationCreatedRepository;
		this.ratingRepository = ratingRepository;
		this.timeRepository = timeRepository;
		this.visitRepository = visitRepository;
		this.promotionService = promotionService;
		this.ratingCreatedRepository = ratingCreatedRepository;
		this.promotionRepository = promotionRepository;
		this.badDayRepository = badDayRepository;
		this.sessionService = sessionService;
	}

	@Override
	public List<LocationUserViewDTO> getAll(long userId) {
		return toUserView(locationRepository.findAll(), userId);
	}

	@Override
	public List<LocationAdminViewDTO> getAll() {
		return toAdminView(locationRepository.findAll());
	}

	@Override
	public List<LocationUserViewDTO> getPromoted(long userId) {
		return toUserView(((Set<Location>)promotionService.getPromotedLocations()).stream().collect(Collectors.toList()), userId);
	}

	@Override
	public List<LocationAdminViewDTO> getPromoted() {
		return toAdminView(((Set<Location>)promotionService.getPromotedLocations()).stream().collect(Collectors.toList()));
	}

	@Override
	public LocationUserViewDTO get(long locationId, long userId) throws LocationNotFoundException {
		Location l = locationRepository.findById(locationId).orElse(null);
		if(l == null)
			throw new LocationNotFoundException();
		return toUserView(l, userId);
	}

	@Override
	public LocationAdminViewDTO get(long locationId) throws LocationNotFoundException {
		Location l = locationRepository.findById(locationId).orElse(null);
		if(l == null)
			throw new LocationNotFoundException();
		return toAdminView(l);
	}

	@Override
	public void addLocation(NewLocationDTO newLocation) throws LocationTypeNotFoundException {
		System.out.println(newLocation.getTypeId());
		LocationType type = typeRepository.findById(newLocation.getTypeId()).orElse(null);
		if(type == null)
			throw new LocationTypeNotFoundException();
		Location l = new Location(type, newLocation.getName(), newLocation.getAddress(), newLocation.getDescription(), null);
		l = locationRepository.save(l);
		LocationCreated lc = new LocationCreated(l, new Date());
		lc = locationCreatedRepository.save(lc);
		sessionService.getPromotionSession().getEntryPoint("new location").insert(lc);
	}

	@Override
	public void deleteLocation(long locationId) throws LocationNotFoundException {
		Location l = locationRepository.findById(locationId).orElse(null);
		if(l == null)
			throw new LocationNotFoundException();
		sessionService.getPromotionSession().insert(new LocationDeleted(l));
		visitRepository.findAll().stream().filter(v -> v.getLocation().getId() == l.getId()).forEach(v -> visitRepository.delete(v));
		timeRepository.findAll().stream().filter(t -> t.getLocation().getId() == l.getId()).forEach(t -> timeRepository.delete(t));
		ratingCreatedRepository.findAll().stream().filter(rc -> rc.getLocation().getId() == l.getId()).forEach(rc -> ratingCreatedRepository.delete(rc));
		locationCreatedRepository.findAll().stream().filter(lc -> lc.getLocation().getId() == l.getId()).forEach(lc -> locationCreatedRepository.delete(lc));
		ratingRepository.findAll().stream().filter(r -> r.getLocation().getId() == l.getId()).forEach(r -> ratingRepository.delete(r));
		badDayRepository.findAll().stream().filter(b -> b.getLocation().getId() == l.getId()).forEach(b -> badDayRepository.delete(b));
		promotionRepository.findAll().stream().filter(p -> p.getLocation().getId() == l.getId()).forEach(p -> promotionRepository.delete(p));
		locationRepository.delete(l);
	}

	@Override
	public void saveLocationPageVisited(LocationPageVisitedDTO newVisit) throws LocationNotFoundException {
		Location l = locationRepository.findById(newVisit.getLocationId()).orElse(null);
		if(l == null)
			throw new LocationNotFoundException();
		LocationInfoPageVisited visit = new LocationInfoPageVisited(l, newVisit.getCreated(), newVisit.getDuration());
		visit = visitRepository.save(visit);
		sessionService.getPromotionSession().insert(visit);
	}

	@Override
	public void addTimeEstimation(long locationId, int timeInMinutes) throws LocationNotFoundException {
		Location l = locationRepository.findById(locationId).orElse(null);
		if(l == null)
			throw new LocationNotFoundException();
		timeRepository.save(new TimeSpentAtLocation(l, timeInMinutes));
	}

	private double getAvgTime(long locationId) {
		List<TimeSpentAtLocation> times = timeRepository.findAll().stream().filter(t -> t.getLocation().getId() == locationId).collect(Collectors.toList());
		if(times.size() > 0) {
			double sum = 0;
			for(TimeSpentAtLocation time : times)
				sum += time.getEstimatedTimeInMinutes();
			return sum / times.size();
		}
		else
			return 0;
	}
	
	private long getLikeCount(long locationId) {
		return ratingRepository.findAll().stream().filter(r -> r.getLocation().getId() == locationId && r.getType() == RatingType.LIKE).count();
	}
	
	private long getDislikeCount(long locationId) {
		return ratingRepository.findAll().stream().filter(r -> r.getLocation().getId() == locationId && r.getType() == RatingType.DISLIKE).count();
	}
	
	private boolean userRatedLocation(long userId, long locationId) {
		return ratingRepository.findAll().stream().filter(r -> r.getUser().getId() == userId && r.getLocation().getId() == locationId).count() > 0;
	}
	
	private boolean userLikedLocation(long userId, long locationId) {
		return ratingRepository.findAll().stream().filter(r -> r.getUser().getId() == userId && r.getLocation().getId() == locationId && r.getType() == RatingType.LIKE).count() > 0;
	}
	
	@Override
	public List<LocationUserViewDTO> toUserView(List<Location> locations, long userId) {
		List<LocationUserViewDTO> result = new ArrayList<LocationUserViewDTO>();
		for(Location l : locations)
			result.add(toUserView(l, userId));
		return result;
	}
	
	private List<LocationAdminViewDTO> toAdminView(List<Location> locations) {
		List<LocationAdminViewDTO> result = new ArrayList<LocationAdminViewDTO>();
		for(Location l : locations)
			result.add(toAdminView(l));
		return result;
	}
	
	private LocationUserViewDTO toUserView(Location l, long userId) {
		return new LocationUserViewDTO(l.getId(),
				   l.getType() != null ? l.getType().getName() : null,
				   l.getName(),
				   l.getAddress(),
				   l.getDescription(),
				   getAvgTime(l.getId()),
				   getLikeCount(l.getId()),
				   getDislikeCount(l.getId()),
				   userRatedLocation(userId, l.getId()),
				   userLikedLocation(userId, l.getId()));
	}
	
	private LocationAdminViewDTO toAdminView(Location l) {
		return new LocationAdminViewDTO(l.getId(),
				   l.getType() != null ? l.getType().getName() : null,
				   l.getName(),
				   l.getAddress(),
				   l.getDescription(),
				   getAvgTime(l.getId()),
				   getLikeCount(l.getId()),
				   getDislikeCount(l.getId()));
	}
}
