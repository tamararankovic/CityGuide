package sbnz.app.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KieSessionService {

	private final KieContainer kieContainer;
    private KieSession recommendationSession;
    
    private final String RECOMMENDATION_SESSION_NAME = "recommendation-session";

    @Autowired
    public KieSessionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    
    public KieSession getRecommendationSession() {
    	if (this.recommendationSession == null) {
        	recommendationSession = kieContainer.newKieSession(RECOMMENDATION_SESSION_NAME);
    	}
        return recommendationSession;
    }
    
    public void releaseRecommendationSession(){
        this.recommendationSession.dispose();
        this.recommendationSession = null;
    }
}
