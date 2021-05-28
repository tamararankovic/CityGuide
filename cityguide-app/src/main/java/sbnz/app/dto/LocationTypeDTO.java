package sbnz.app.dto;

import java.util.Set;

public class LocationTypeDTO {

	private long id;
	private String name;
	private Set<String> features;
	
	public LocationTypeDTO() {
		super();
	}
	
	public LocationTypeDTO(long id, String name, Set<String> features) {
		super();
		this.id = id;
		this.name = name;
		this.features = features;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getFeatures() {
		return features;
	}
	public void setFeatures(Set<String> features) {
		this.features = features;
	}
}
