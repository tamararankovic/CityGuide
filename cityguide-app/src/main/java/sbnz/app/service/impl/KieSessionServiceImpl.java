package sbnz.app.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.service.KieSessionService;

@Service
public class KieSessionServiceImpl implements KieSessionService {

	private final KieContainer kieContainer;
    private KieSession promotionSession;
    
    private final String RECOMMENDATION_SESSION_NAME = "recommendation-session";
    private final String PROMOTION_SESSION_NAME = "promotion-session";

    @Autowired
    public KieSessionServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    
    public KieSession getRecommendationSession() {
        return kieContainer.newKieSession(RECOMMENDATION_SESSION_NAME);
    }
    
    public KieSession getPromotionSession() {
    	if (this.promotionSession == null) {
        	promotionSession = kieContainer.newKieSession(PROMOTION_SESSION_NAME);
    	}
        return promotionSession;
    }
    
    public void releasePromotionSession() {
        this.promotionSession.dispose();
        this.promotionSession = null;
    }
}
