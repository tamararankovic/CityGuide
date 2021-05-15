package sbnz.app.controller;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.app.model.Location;
import sbnz.app.model.LocationType;
import sbnz.app.model.Rating;
import sbnz.app.model.RatingType;
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
		KieSession kieSession = kieContainer.newKieSession();
		List<User> users = repo.findAll();
		for(User u : users) {
			kieSession.insert(u);
		}
		Location l = new Location(0, new LocationType(), "", "", "", "");
		kieSession.insert(l);
		kieSession.insert(new Rating(0, null, l, RatingType.LIKE));
		System.out.println(kieSession.getFactCount());   
		kieSession.fireAllRules();
		kieSession.dispose();
	}

}
