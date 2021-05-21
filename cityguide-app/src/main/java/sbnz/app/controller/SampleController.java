package sbnz.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sbnz.app.exception.UnauthenticatedException;
import sbnz.app.exception.UnauthorizedException;
import sbnz.app.model.UserRole;
import sbnz.app.security.JwtHelper;
import sbnz.app.service.AuthService;
import sbnz.app.service.SampleService;


@RestController
public class SampleController {

	private SampleService service;
	private JwtHelper jwtHelper;
	private AuthService authService;
	
	@Autowired
	public SampleController(SampleService service, JwtHelper jwtHelper, AuthService authService) {
		this.service = service;
		this.jwtHelper = jwtHelper;
		this.authService = authService;
	}

	@GetMapping(value = "/test")
	public void test(HttpServletRequest request) {
		try {
			String jwt = jwtHelper.getJwtFromRequest(request);
			authService.getAuthorized(jwt, UserRole.BASIC_USER);
			service.test();
		} catch (UnauthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not logged in");
		} catch (UnauthorizedException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have required permissions");
		}
	}

}
