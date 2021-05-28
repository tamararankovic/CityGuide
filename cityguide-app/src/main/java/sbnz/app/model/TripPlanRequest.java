package sbnz.app.model;

import java.util.Set;

public class TripPlanRequest {

	private User user;
	private Set<LocationType> preferredTypes;
	private int tripDurationInDays;
	
	public TripPlanRequest() {
		super();
	}
	
	public TripPlanRequest(User user, Set<LocationType> preferredTypes, int tripDurationInDays) {
		super();
		this.user = user;
		this.preferredTypes = preferredTypes;
		this.tripDurationInDays = tripDurationInDays;
	}
	
	public User getUser() {
		return user;
	}
	public Set<LocationType> getPreferredTypes() {
		return preferredTypes;
	}
	public int getTripDurationInDays() {
		return tripDurationInDays;
	}
}
