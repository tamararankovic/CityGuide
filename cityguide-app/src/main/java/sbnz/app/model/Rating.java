package sbnz.app.model;

import java.time.LocalDateTime;

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
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Location location;
	
	@Column(name = "type", nullable = false)
	private RatingType type;
	
	@Column(name = "lastModified", nullable = false)
	private LocalDateTime lastModified;
	
	public Rating() {
		super();
		lastModified = LocalDateTime.now();
	}
	
	public Rating(User user, Location location, RatingType type) {
		super();
		this.user = user;
		this.location = location;
		this.type = type;
		lastModified = LocalDateTime.now();
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
	public LocalDateTime getLastModified() {
		return lastModified;
	}
	public void setType(RatingType type) {
		this.type = type;
		lastModified = LocalDateTime.now();
	}

	@Override
	public boolean equals(Object obj) {
		return id == ((Rating)obj).id;
	}
}
