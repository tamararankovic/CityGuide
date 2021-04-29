package com.sample.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class TripPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TripPlanRequest request;
	
	@ManyToMany
	@JoinTable(name = "trip_plan_location", 
	joinColumns = @JoinColumn(name = "trip_plan_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
	private List<Location> recommendedLocations;
	
	public TripPlan() {
		super();
	}
	
	public TripPlan(long id, TripPlanRequest request, List<Location> recommendedLocations) {
		super();
		this.id = id;
		this.request = request;
		this.recommendedLocations = recommendedLocations;
	}
	
	public long getId() {
		return id;
	}
	public TripPlanRequest getRequest() {
		return request;
	}
	public List<Location> getRecommendedLocations() {
		return recommendedLocations;
	}
}
