package sbnz.app.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.app.fact.event.RatingCreated;
import sbnz.app.model.Feature;
import sbnz.app.model.Location;
import sbnz.app.model.LocationType;
import sbnz.app.model.Rating;
import sbnz.app.model.RatingType;
import sbnz.app.model.TripPlanRequest;
import sbnz.app.model.User;
import sbnz.app.repository.UserRepository;


@RestController
public class SampleController {

	private final KieContainer kieContainer;

	private static Logger log = LoggerFactory.getLogger(SampleController.class);

	private UserRepository repo;
	
	@Autowired
	public SampleController(KieContainer kieContainer, UserRepository repo) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
		this.repo = repo;
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public void getQuestions() {
		KieSession kieSession = kieContainer.newKieSession("session");
		List<User> users = repo.findAll();
		for(User u : users) {
			kieSession.insert(u);
		}
		for(Feature f : Feature.values()) {
			kieSession.insert(f);
		}
		Set<Feature> f = new HashSet<Feature>();
		f.add(Feature.ART);
		Set<Feature> f2 = new HashSet<Feature>(f);
		f2.add(Feature.FOOD_AND_DRINK);
		User u = new User();
		LocationType lt = new LocationType(0, "gallery", f);
		LocationType lt2 = new LocationType(1, "cinema", f2);
		Location l = new Location(0, lt, "", "", "", "");
		Location l2 = new Location(1, lt2, "", "", "", "");
		Set<LocationType> types = new HashSet<LocationType>();
		types.add(lt);
		TripPlanRequest tpr = new TripPlanRequest(0, u, null, types, 2);
		
		Rating r = new Rating(0, u, l2, RatingType.LIKE);
		
		RatingCreated rc1 = new RatingCreated(r, new Date(), RatingType.LIKE);
		RatingCreated rc2 = new RatingCreated(r, new Date(), RatingType.LIKE);
		RatingCreated rc3 = new RatingCreated(r, new Date(), RatingType.LIKE);
		
		kieSession.insert(lt);
		kieSession.insert(lt2);
		kieSession.insert(l);
		kieSession.insert(tpr);
		kieSession.insert(r);
		kieSession.insert(rc1);
		kieSession.insert(rc2);
		kieSession.insert(rc3);
		System.out.println(kieSession.getFactCount());   
		kieSession.fireAllRules();
		kieSession.dispose();
	}

}
