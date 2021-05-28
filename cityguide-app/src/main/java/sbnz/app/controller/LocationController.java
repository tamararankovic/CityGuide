package sbnz.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sbnz.app.dto.LocationAdminViewDTO;
import sbnz.app.dto.LocationPageVisitedDTO;
import sbnz.app.dto.LocationUserViewDTO;
import sbnz.app.dto.NewLocationDTO;
import sbnz.app.exception.LocationNotFoundException;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.exception.UnauthenticatedException;
import sbnz.app.exception.UnauthorizedException;
import sbnz.app.model.User;
import sbnz.app.model.UserRole;
import sbnz.app.security.JwtHelper;
import sbnz.app.service.AuthService;
import sbnz.app.service.LocationService;

@RestController
@RequestMapping(value = "location")
public class LocationController {

	private LocationService locationService;
	private AuthService authService;
	private JwtHelper jwtHelper;
	
	@Autowired
	public LocationController(LocationService locationService, AuthService authService, JwtHelper jwtHelper) {
		super();
		this.locationService = locationService;
		this.authService = authService;
		this.jwtHelper = jwtHelper;
	}

	@GetMapping(value = "user")
	public List<LocationUserViewDTO> getAllUser(HttpServletRequest request) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			User user = authService.getAuthorized(jwt, UserRole.BASIC_USER);
			return locationService.getAll(user.getId());
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		}
	}
	
	@GetMapping(value = "admin")
	public List<LocationAdminViewDTO> getAllAdmin(HttpServletRequest request) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.ADMIN);
			return locationService.getAll();
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		}
	}
	
	@GetMapping(value = "promoted/user")
	public List<LocationUserViewDTO> getPromotedUser(HttpServletRequest request) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			User user = authService.getAuthorized(jwt, UserRole.BASIC_USER);
			return locationService.getPromoted(user.getId());
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		}
	}
	
	@GetMapping(value = "promoted/admin")
	public List<LocationAdminViewDTO> getPromotedAdmin(HttpServletRequest request) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.ADMIN);
			return locationService.getPromoted();
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		}
	}
	
	@GetMapping(value = "user/{id}")
	public LocationUserViewDTO getUser(HttpServletRequest request, @PathVariable("id") long locationId) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			User user = authService.getAuthorized(jwt, UserRole.BASIC_USER);
			return locationService.get(user.getId(), locationId);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found");
		}
	}
	
	@GetMapping(value = "admin/{id}")
	public LocationAdminViewDTO getAdmin(HttpServletRequest request, @PathVariable("id") long locationId) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.ADMIN);
			return locationService.get(locationId);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found");
		}
	}
	
	@PostMapping()
	public void addLocation(HttpServletRequest request, @RequestBody NewLocationDTO dto) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.ADMIN);
			locationService.addLocation(dto);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationTypeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location type not found");
		}
	}
	
	@DeleteMapping(value = "{id}")
	public void deleteLocation(HttpServletRequest request, @PathVariable("id") long locationId) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.ADMIN);
			locationService.deleteLocation(locationId);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found");
		}
	}
	
	@PostMapping(value = "visited")
	public void saveLocationVisited(HttpServletRequest request, @RequestBody LocationPageVisitedDTO dto) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.BASIC_USER);
			locationService.saveLocationPageVisited(dto);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found");
		}
	}
	
	@PostMapping(value = "time/{id}/{time}")
	public void saveTimeEstimation(HttpServletRequest request, @PathVariable("id") long locationId, @PathVariable("time") int minutes) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.BASIC_USER);
			locationService.addTimeEstimation(locationId, minutes);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found");
		}
	}
}
