package sbnz.app.service;

import java.util.Set;

import sbnz.app.dto.LocationTypeDTO;
import sbnz.app.dto.NewLocationTypeDTO;
import sbnz.app.exception.LocationTypeNotFoundException;

public interface LocationTypeService {

	public void addLocationType(NewLocationTypeDTO newType);
	
	public void deleteLocationType(long typeId) throws LocationTypeNotFoundException;
	
	public Set<String> getFeatures();

	public Set<LocationTypeDTO> getAll();
}
