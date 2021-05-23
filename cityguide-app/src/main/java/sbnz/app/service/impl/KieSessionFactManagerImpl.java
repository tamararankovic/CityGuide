package sbnz.app.service.impl;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.data.jpa.repository.JpaRepository;

public class KieSessionFactManagerImpl<FactType> {

	public void insert(KieSession session, List<FactType> facts) {
		for(FactType fact : facts)
			session.insert(fact);
	}
	
	@SuppressWarnings("unchecked")
	public void persist(KieSession session, String queryName, JpaRepository<FactType, Long> destination) {
		QueryResults results = session.getQueryResults(queryName); 
		for ( QueryResultsRow row : results ) {
		    destination.save((FactType)row.get("$result"));
		}
	}
}
