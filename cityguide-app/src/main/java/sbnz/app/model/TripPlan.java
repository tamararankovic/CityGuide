package sbnz.app.model;

import java.util.List;

public class TripPlan {

	private TripPlanRequest request;
	private List<Location> recommendedLocations;
	
	public TripPlan() {
		super();
	}
	
	public TripPlan(TripPlanRequest request, List<Location> recommendedLocations) {
		super();
		this.request = request;
		this.recommendedLocations = recommendedLocations;
	}

	public TripPlanRequest getRequest() {
		return request;
	}
	public List<Location> getRecommendedLocations() {
		return recommendedLocations;
	}
}
