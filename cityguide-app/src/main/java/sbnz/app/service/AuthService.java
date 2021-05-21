package sbnz.app.service;

import sbnz.app.exception.UnauthenticatedException;
import sbnz.app.exception.UnauthorizedException;
import sbnz.app.exception.UserAlreadyExistsException;
import sbnz.app.exception.UserNotFoundException;
import sbnz.app.model.User;
import sbnz.app.model.UserRole;

public interface AuthService {

	public void register(User user) throws UserAlreadyExistsException;
	
	public String logIn(String email, String password) throws UserNotFoundException;
	
	public User getAuthenticated(String jwt) throws UnauthenticatedException;
	
	public User getAuthorized(String jwt, UserRole role) throws UnauthenticatedException, UnauthorizedException;
}
