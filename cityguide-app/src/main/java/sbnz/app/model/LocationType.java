package sbnz.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocationType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ElementCollection(fetch = FetchType.LAZY)
	private Set<Feature> features = new HashSet<Feature>();
	
	public LocationType() {
		super();
	}
	
	public LocationType(long id, String name, Set<Feature> features) {
		super();
		this.id = id;
		this.name = name;
		this.features = features;
	}
	
	public LocationType(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Set<Feature> getFeatures() {
		return features;
	}
	public void addFeature(Feature feature) {
		features.add(feature);
	}

	@Override
	public boolean equals(Object obj) {
		return id == ((LocationType)obj).id;
	}
}
