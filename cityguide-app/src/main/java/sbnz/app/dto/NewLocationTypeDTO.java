package sbnz.app.dto;

import java.util.List;

public class NewLocationTypeDTO {

	private String name;
	private List<String> features;
	
	public NewLocationTypeDTO() {
		super();
	}
	
	public NewLocationTypeDTO(String name, List<String> features) {
		super();
		this.name = name;
		this.features = features;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFeatures() {
		return features;
	}
	public void setFeatures(List<String> features) {
		this.features = features;
	}
}
