package sbnz.app.dto;

import java.util.List;

public class TripPlanRequestDTO {

	private List<Long> typeIds;
	private int durationInDays;
	
	public TripPlanRequestDTO() {
		super();
	}
	
	public TripPlanRequestDTO(List<Long> typeIds, int durationInDays) {
		super();
		this.typeIds = typeIds;
		this.durationInDays = durationInDays;
	}
	
	public List<Long> getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(List<Long> typeIds) {
		this.typeIds = typeIds;
	}
	public int getDurationInDays() {
		return durationInDays;
	}
	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}
}
