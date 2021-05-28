package sbnz.app.fact.event;

import java.io.Serializable;
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
import sbnz.app.model.RatingType;

@Role(Role.Type.EVENT)
@Timestamp("created")
@Entity
public class RatingCreated implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Location location;
	
	@Column(name = "created", nullable = false)
	private Date created;
	
	@Column(name = "type", nullable = false)
	private RatingType type;

	public RatingCreated() {
		super();
		created = new Date();
	}

	public RatingCreated(Location location, Date created, RatingType type) {
		super();
		this.location = location;
		this.created = created;
		this.type = type;
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
	public RatingType getType() {
		return type;
	}
	public void setType(RatingType type) {
		this.type = type;
	}
}
