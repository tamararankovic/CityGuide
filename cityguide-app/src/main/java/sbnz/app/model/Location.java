package sbnz.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private LocationType type;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "imageFilePath", nullable = true)
	private String imageFilePath;
	
	public Location() {
		super();
	}

	public Location(long id, LocationType type, String name, String address, String description, String imageFilePath) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.address = address;
		this.description = description;
		this.imageFilePath = imageFilePath;
	}
	
	public Location(LocationType type, String name, String address, String description, String imageFilePath) {
		super();
		this.type = type;
		this.name = name;
		this.address = address;
		this.description = description;
		this.imageFilePath = imageFilePath;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocationType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getDescription() {
		return description;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
}
