package sbnz.app.service;

import java.util.List;

import sbnz.app.dto.LocationAdminViewDTO;
import sbnz.app.dto.LocationPageVisitedDTO;
import sbnz.app.dto.LocationUserViewDTO;
import sbnz.app.dto.NewLocationDTO;
import sbnz.app.exception.LocationNotFoundException;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.model.Location;

public interface LocationService {

	public List<LocationUserViewDTO> getAll(long userId);
	
	public List<LocationAdminViewDTO> getAll();
	
	public List<LocationUserViewDTO> getPromoted(long userId);
	
	public List<LocationAdminViewDTO> getPromoted();
	
	public LocationUserViewDTO get(long locationId, long userId) throws LocationNotFoundException;
	
	public LocationAdminViewDTO get(long locationId) throws LocationNotFoundException;
	
	public void addLocation(NewLocationDTO newLocation) throws LocationTypeNotFoundException;
	
	public void deleteLocation(long locationId) throws LocationNotFoundException;
	
	public void saveLocationPageVisited(LocationPageVisitedDTO newVisit) throws LocationNotFoundException;

	public void addTimeEstimation(long locationId, int timeInMinutes) throws LocationNotFoundException;

	public List<LocationUserViewDTO> toUserView(List<Location> locations, long userId);
}
