package com.sample;

import java.util.List;

import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.User;
import com.sample.repository.UserRepository;

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
		kieSession.addEventListener(new DebugAgendaEventListener());    
		List<User> users = repo.findAll();
		for(User u : users) {
			kieSession.insert(u);
		}
		System.out.println(kieSession.getFactCount());   
		kieSession.fireAllRules();
		kieSession.dispose();
	}

}
