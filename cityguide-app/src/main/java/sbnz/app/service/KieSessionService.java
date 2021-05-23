package sbnz.app.service;

import org.kie.api.runtime.KieSession;

public interface KieSessionService {

	 public KieSession getRecommendationSession();
	 
	 public void releaseRecommendationSession();
	 
	 public KieSession getPromotionSession();
	 
	 public void releasePromotionSession();
}
