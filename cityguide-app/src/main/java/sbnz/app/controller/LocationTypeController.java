package sbnz.app.controller;

import java.util.Set;

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

import sbnz.app.dto.LocationTypeDTO;
import sbnz.app.dto.NewLocationTypeDTO;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.exception.UnauthenticatedException;
import sbnz.app.exception.UnauthorizedException;
import sbnz.app.model.UserRole;
import sbnz.app.security.JwtHelper;
import sbnz.app.service.AuthService;
import sbnz.app.service.LocationTypeService;

@RestController
@RequestMapping(value = "type")
public class LocationTypeController {

	private LocationTypeService typeService;
	private AuthService authService;
	private JwtHelper jwtHelper;
	
	@Autowired
	public LocationTypeController(LocationTypeService typeService, AuthService authService, JwtHelper jwtHelper) {
		super();
		this.typeService = typeService;
		this.authService = authService;
		this.jwtHelper = jwtHelper;
	}

	@PostMapping()
	public void addLocationType(HttpServletRequest request, @RequestBody NewLocationTypeDTO dto) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.ADMIN);
			typeService.addLocationType(dto);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		}
	}
	
	@DeleteMapping(value = "{id}")
	public void deleteLocationType(HttpServletRequest request, @PathVariable("id") long typeId) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.ADMIN);
			typeService.deleteLocationType(typeId);
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		} catch (LocationTypeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location type not found");
		}
	}
	
	@GetMapping()
	public Set<LocationTypeDTO> getAll(HttpServletRequest request) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthenticated(jwt);
			return typeService.getAll();
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		}
	}
	
	@GetMapping(value = "features")
	public Set<String> getFeatures(HttpServletRequest request) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthenticated(jwt);
			return typeService.getFeatures();
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		}
	}
}
