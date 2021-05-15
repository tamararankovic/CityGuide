package sbnz.app.fact.event;

import java.io.Serializable;
import java.time.LocalDateTime;

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

import sbnz.app.model.Rating;
import sbnz.app.model.RatingType;

@Role(Role.Type.EVENT)
@Timestamp("created")
@Entity
public class RatingCreated implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Rating rating;
	
	@Column(name = "created", nullable = false)
	private LocalDateTime created;
	
	@Column(name = "typeAtCreationTime", nullable = false)
	private RatingType typeAtCreationTime;

	public RatingCreated() {
		super();
		created = LocalDateTime.now();
	}

	public RatingCreated(Rating rating, LocalDateTime created, RatingType typeAtCreationTime) {
		super();
		this.rating = rating;
		this.created = created;
		this.typeAtCreationTime = typeAtCreationTime;
	}

	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public RatingType getTypeAtCreationTime() {
		return typeAtCreationTime;
	}
	public void setTypeAtCreationTime(RatingType typeAtCreationTime) {
		this.typeAtCreationTime = typeAtCreationTime;
	}
}