package sbnz.app.dto;

import java.util.Date;

public class LocationPageVisitedDTO {

	private long locationId;
	private Date created;
	private long duration;
	
	public LocationPageVisitedDTO() {
		super();
	}
	
	public LocationPageVisitedDTO(long locationId, Date created, long duration) {
		super();
		this.locationId = locationId;
		this.created = created;
		this.duration = duration;
	}
	
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
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
}
