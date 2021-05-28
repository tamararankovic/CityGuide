package sbnz.app.dto;

public class NewRatingDTO {

	private long locationId;
	private boolean like;
	
	public NewRatingDTO() {
		super();
	}
	
	public NewRatingDTO(long locationId, boolean like) {
		super();
		this.locationId = locationId;
		this.like = like;
	}
	
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
}
