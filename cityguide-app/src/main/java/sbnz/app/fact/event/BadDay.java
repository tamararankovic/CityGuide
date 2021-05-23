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

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import sbnz.app.model.Location;

@Role(Role.Type.EVENT)
@Timestamp("created")
@Entity
public class BadDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Location location;
	
	@Column(name = "created", nullable = false)
	private Date created;

	public BadDay() {
		super();
	}

	public BadDay(Location location, Date created) {
		super();
		this.location = location;
		this.created = created;
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
}
