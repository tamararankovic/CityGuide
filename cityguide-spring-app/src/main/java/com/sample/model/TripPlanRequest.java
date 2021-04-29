package com.sample.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class TripPlanRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	@Column(name = "created", nullable = false)
	private LocalDateTime created;
	
	@ManyToMany
	@JoinTable(name = "trip_request_location_type", 
	joinColumns = @JoinColumn(name = "trip_request_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "location_type_id", referencedColumnName = "id"))
	private Set<LocationType> preferredTypes;
	private int tripDurationInDays;
	
	public TripPlanRequest() {
		super();
	}
	
	public TripPlanRequest(long id, User user, LocalDateTime created, Set<LocationType> preferredTypes,
			int tripDurationInDays) {
		super();
		this.id = id;
		this.user = user;
		this.created = created;
		this.preferredTypes = preferredTypes;
		this.tripDurationInDays = tripDurationInDays;
	}
	
	public long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public Set<LocationType> getPreferredTypes() {
		return preferredTypes;
	}
	public int getTripDurationInDays() {
		return tripDurationInDays;
	}
}
