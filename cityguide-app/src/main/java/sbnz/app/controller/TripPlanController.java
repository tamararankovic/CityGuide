package sbnz.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sbnz.app.dto.TripPlanDTO;
import sbnz.app.dto.TripPlanRequestDTO;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.exception.UnauthenticatedException;
import sbnz.app.exception.UnauthorizedException;
import sbnz.app.model.User;
import sbnz.app.model.UserRole;
import sbnz.app.security.JwtHelper;
import sbnz.app.service.AuthService;
import sbnz.app.service.TripPlanService;

@RestController
@RequestMapping(value = "trip")
public class TripPlanController {

	private TripPlanService tripService;
	private AuthService authService;
	private JwtHelper jwtHelper;
	
	@Autowired
	public TripPlanController(TripPlanService tripService, AuthService authService, JwtHelper jwtHelper) {
		super();
		this.tripService = tripService;
		this.authService = authService;
		this.jwtHelper = jwtHelper;
	}

	@PostMapping()
	public TripPlanDTO planTrip(HttpServletRequest request, @RequestBody TripPlanRequestDTO dto) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			User user = authService.getAuthorized(jwt, UserRole.BASIC_USER);
			return tripService.planTrip(dto, user);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationTypeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location type not found");
		} 
	}
}
