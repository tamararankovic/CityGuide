package sbnz.app.dto;

import java.util.List;

public class TripPlanDTO {

	private TripPlanRequestDTO request;
	private List<LocationUserViewDTO> locations;
	
	public TripPlanDTO() {
		super();
	}
	
	public TripPlanDTO(TripPlanRequestDTO request, List<LocationUserViewDTO> locations) {
		super();
		this.request = request;
		this.locations = locations;
	}
	
	public TripPlanRequestDTO getRequest() {
		return request;
	}
	public void setRequest(TripPlanRequestDTO request) {
		this.request = request;
	}
	public List<LocationUserViewDTO> getLocations() {
		return locations;
	}
	public void setLocations(List<LocationUserViewDTO> locations) {
		this.locations = locations;
	}
}
