package sbnz.app.dto;

public class LocationAdminViewDTO {

	private long id;
	private String type;
	private String name;
	private String address;
	private String description;
	private double avgTimeSpent;
	private long likes;
	private long dislikes;
	
	public LocationAdminViewDTO() {
		super();
	}
	
	public LocationAdminViewDTO(long id, String type, String name, String address, String description,
			double avgTimeSpent, long likes, long dislikes) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.address = address;
		this.description = description;
		this.avgTimeSpent = avgTimeSpent;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAvgTimeSpent() {
		return avgTimeSpent;
	}
	public void setAvgTimeSpent(double avgTimeSpent) {
		this.avgTimeSpent = avgTimeSpent;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public long getDislikes() {
		return dislikes;
	}
	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
}
