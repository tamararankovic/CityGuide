package sbnz.app.service;

import sbnz.app.dto.TripPlanDTO;
import sbnz.app.dto.TripPlanRequestDTO;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.model.User;

public interface TripPlanService {

	public TripPlanDTO planTrip(TripPlanRequestDTO request, User user) throws LocationTypeNotFoundException;
}
