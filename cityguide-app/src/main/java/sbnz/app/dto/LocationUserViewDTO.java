package sbnz.app.dto;

public class LocationUserViewDTO {

	private long id;
	private String type;
	private String name;
	private String address;
	private String description;
	private double avgTimeSpent;
	private long likes;
	private long dislikes;
	private boolean rated;
	private boolean liked;
	
	public LocationUserViewDTO() {
		super();
	}
	
	public LocationUserViewDTO(long id, String type, String name, String address, String description,
			double avgTimeSpent, long likes, long dislikes, boolean rated, boolean liked) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.address = address;
		this.description = description;
		this.avgTimeSpent = avgTimeSpent;
		this.likes = likes;
		this.dislikes = dislikes;
		this.rated = rated;
		this.liked = liked;
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
	public boolean isRated() {
		return rated;
	}
	public void setRated(boolean rated) {
		this.rated = rated;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
}
