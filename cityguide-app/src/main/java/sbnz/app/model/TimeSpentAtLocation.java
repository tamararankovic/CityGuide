package sbnz.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TimeSpentAtLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Location location;
	
	@Column(name = "estimatedTimeInMinutes", nullable = false)
	private int estimatedTimeInMinutes;

	public TimeSpentAtLocation() {
		super();
	}

	public TimeSpentAtLocation(long id, User user, Location location, int estimatedTimeInMinutes) {
		super();
		this.id = id;
		this.user = user;
		this.location = location;
		this.estimatedTimeInMinutes = estimatedTimeInMinutes;
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
	public int getEstimatedTimeInMinutes() {
		return estimatedTimeInMinutes;
	}
}
