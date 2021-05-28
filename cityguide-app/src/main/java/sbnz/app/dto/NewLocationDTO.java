package sbnz.app.dto;

public class NewLocationDTO {

	private long typeId;
	private String name;
	private String address;
	private String description;
	
	public NewLocationDTO() {
		super();
	}
	
	public NewLocationDTO(long typeId, String name, String address, String description) {
		super();
		this.typeId = typeId;
		this.name = name;
		this.address = address;
		this.description = description;
	}
	
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
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
}
