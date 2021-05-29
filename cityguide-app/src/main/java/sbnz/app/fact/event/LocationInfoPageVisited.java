package sbnz.app.fact.event;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.kie.api.definition.type.Duration;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import sbnz.app.model.Location;

@Role(Role.Type.EVENT)
@Timestamp("created")
@Duration("duration")
@Entity
public class LocationInfoPageVisited {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Location location;
	
	@Column(name = "created", nullable = false)
	private Date created;
	
	@Column(name = "duration", nullable = false)
	private long duration;

	public LocationInfoPageVisited() {
		super();
	}

	public LocationInfoPageVisited(Location location, Date created, long duration) {
		super();
		this.location = location;
		this.created = created;
		this.duration = duration;
	}

	public long getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
}
