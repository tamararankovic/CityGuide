package com.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Location location;
	
	@Column(name = "type", nullable = false)
	private RatingType type;
	
	public Rating() {
		super();
	}
	
	public Rating(long id, User user, Location location, RatingType type) {
		super();
		this.id = id;
		this.user = user;
		this.location = location;
		this.type = type;
	}
	
	public long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public Location getLocation() {
		return location;
	}
	public RatingType getType() {
		return type;
	}
}
