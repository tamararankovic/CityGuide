package sbnz.app.fact.event;

import org.kie.api.definition.type.Role;

import sbnz.app.model.LocationType;

@Role(Role.Type.EVENT)
public class LocationTypeDeleted {

	private LocationType locationType;

	public LocationTypeDeleted(LocationType locationType) {
		super();
		this.locationType = locationType;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}
}
