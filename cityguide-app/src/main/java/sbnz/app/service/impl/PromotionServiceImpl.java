package sbnz.app.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.fact.event.BadDay;
import sbnz.app.fact.event.LocationCreated;
import sbnz.app.fact.event.LocationInfoPageVisited;
import sbnz.app.fact.event.LocationRetrievalRequest;
import sbnz.app.fact.event.LocationTypeCreated;
import sbnz.app.fact.event.Promotion;
import sbnz.app.fact.event.RatingCreated;
import sbnz.app.model.Location;
import sbnz.app.repository.BadDayRepository;
import sbnz.app.repository.LocationCreatedRepository;
import sbnz.app.repository.LocationInfoPageVisitedRepository;
import sbnz.app.repository.LocationTypeCreatedRepository;
import sbnz.app.repository.PromotionRepository;
import sbnz.app.repository.RatingCreatedRepository;
import sbnz.app.service.KieSessionService;
import sbnz.app.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	private RatingCreatedRepository ratingCreatedRepository;
	private LocationCreatedRepository locationCreatedRepository;
	private LocationTypeCreatedRepository locationTypeCreatedRepository;
	private PromotionRepository promotionRepository;
	private BadDayRepository badDayRepository;
	private LocationInfoPageVisitedRepository locationInfoPageVisitedRepository;
	private KieSessionService kieSessionService;
	
	@Autowired
	public PromotionServiceImpl(RatingCreatedRepository ratingCreatedRepository,
			LocationCreatedRepository locationCreatedRepository,
			LocationTypeCreatedRepository locationTypeCreatedRepository,
			PromotionRepository promotionRepository,
			BadDayRepository badDayRepository, LocationInfoPageVisitedRepository locationInfoPageVisitedRepository,
			KieSessionService kieSessionService) {
		super();
		this.ratingCreatedRepository = ratingCreatedRepository;
		this.locationCreatedRepository = locationCreatedRepository;
		this.locationTypeCreatedRepository = locationTypeCreatedRepository;
		this.promotionRepository = promotionRepository;
		this.badDayRepository = badDayRepository;
		this.locationInfoPageVisitedRepository = locationInfoPageVisitedRepository;
		this.kieSessionService = kieSessionService;
	}
	
	@PostConstruct
	private void initSession() {
		KieSession promotionSession = kieSessionService.getPromotionSession();
		insertAll(promotionSession);
		new Thread(new Runnable() {
			@Override
			public void run() {
				promotionSession.fireUntilHalt();
			}
		}).start();
		System.out.println("All facts inserted in promotion session");
	}
	
	public Set<Location> getPromotedLocations() {
		KieSession promotionSession = kieSessionService.getPromotionSession();
		FactHandle requestHandle = promotionSession.insert(new LocationRetrievalRequest());
		Set<Location> locations = retrievePromotedLocationsFromQuery(promotionSession);
		promotionSession.delete(requestHandle);
		System.out.println(locations);
		return locations;
	}
	
	@SuppressWarnings("unchecked")
	private Set<Location> retrievePromotedLocationsFromQuery(KieSession session) {
		Set<Location> locations = new HashSet<Location>();
		QueryResults results = session.getQueryResults("getPromotedLocations"); 
		for ( QueryResultsRow row : results ) {
			locations = (Set<Location>)row.get("$locations");
			break;
		}
		return locations;
	}
	
	@PreDestroy
	private void getReadyForShutdown() {
		KieSession promotionSession = kieSessionService.getPromotionSession();
		persistAll(promotionSession);
		System.out.println("All facts from promotion session persisted");
		promotionSession.halt();
		kieSessionService.releasePromotionSession();
	}
	
	private void insertAll(KieSession session) {
		new KieSessionFactManagerImpl<RatingCreated>().insert(session, ratingCreatedRepository.findAll());
		new KieSessionFactManagerImpl<LocationCreated>().insert(session, locationCreatedRepository.findAll());
		new KieSessionFactManagerImpl<LocationTypeCreated>().insert(session, locationTypeCreatedRepository.findAll());
		new KieSessionFactManagerImpl<Promotion>().insert(session, promotionRepository.findAll());
		new KieSessionFactManagerImpl<BadDay>().insert(session, badDayRepository.findAll());
		new KieSessionFactManagerImpl<LocationInfoPageVisited>().insert(session, locationInfoPageVisitedRepository.findAll());
	}
	
	private void persistAll(KieSession session) {
		new KieSessionFactManagerImpl<Promotion>().persist(session, "getPromotions", promotionRepository);
		new KieSessionFactManagerImpl<BadDay>().persist(session, "getBadDays", badDayRepository);
	}
}
