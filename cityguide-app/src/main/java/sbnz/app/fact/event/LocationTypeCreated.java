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

import sbnz.app.model.LocationType;

@Role(Role.Type.EVENT)
@Timestamp("created")
@Entity
public class LocationTypeCreated implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private LocationType locationType;
	
	@Column(name = "created", nullable = false)
	private Date created;

	public LocationTypeCreated() {
		super();
	}

	public LocationTypeCreated(LocationType locationType, Date created) {
		super();
		this.locationType = locationType;
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}