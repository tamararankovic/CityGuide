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

import org.kie.api.definition.type.Duration;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import sbnz.app.fact.PromotionEvalType;
import sbnz.app.model.Location;

@Role(Role.Type.EVENT)
@Timestamp("created")
@Duration("duration")
@Entity
public class Promotion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Location location;
	
	@Column(name = "created", nullable = false)
	private Date created;
	
	@Column(name = "duration", nullable = false)
	private long duration;
	
	@Column(name = "type", nullable = false)
	PromotionEvalType type;
	
	@Column(name = "processed", nullable = false)
	private boolean processed;

	public Promotion() {
		super();
		this.type = PromotionEvalType.UNSUCCESSFUL;
		this.processed = false;
	}

	public Promotion(Location location, Date created, long duration) {
		super();
		this.location = location;
		this.created = created;
		this.duration = duration;
		this.type = PromotionEvalType.UNSUCCESSFUL;
		this.processed = false;
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

	public PromotionEvalType getType() {
		return type;
	}

	public void setType(PromotionEvalType type) {
		this.type = type;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
