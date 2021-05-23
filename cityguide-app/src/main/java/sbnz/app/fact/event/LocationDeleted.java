package sbnz.app.fact.event;

import org.kie.api.definition.type.Role;

import sbnz.app.model.Location;

@Role(Role.Type.EVENT)
public class LocationDeleted {

	private Location location;
	
	public LocationDeleted(Location location) {
		super();
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
