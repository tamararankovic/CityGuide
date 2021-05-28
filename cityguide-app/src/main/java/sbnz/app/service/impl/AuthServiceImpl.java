package sbnz.app.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.exception.UnauthenticatedException;
import sbnz.app.exception.UnauthorizedException;
import sbnz.app.exception.UserAlreadyExistsException;
import sbnz.app.exception.UserNotFoundException;
import sbnz.app.model.User;
import sbnz.app.model.UserRole;
import sbnz.app.repository.UserRepository;
import sbnz.app.security.JwtHelper;
import sbnz.app.security.UserClaims;
import sbnz.app.service.AuthService;
import sbnz.app.util.PasswordEncoder;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private JwtHelper jwtHelper;
	
	@Autowired	
	public AuthServiceImpl(UserRepository userRepository, JwtHelper jwtHelper) {
		this.userRepository = userRepository;
		this.jwtHelper = jwtHelper;
	}

	@Override
	public void register(User user) throws UserAlreadyExistsException {
		if(userRepository.findByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException();
		} else {
			user.setPassword(PasswordEncoder.cryptWithMD5(user.getPassword()));
			userRepository.save(user);
		}
	}

	@Override
	public String logIn(String email, String password) throws UserNotFoundException {
		String encodedPassword = PasswordEncoder.cryptWithMD5(password);
		System.out.println("password: " + encodedPassword);
		User user = userRepository.findByEmailAndPassword(email, encodedPassword);
		if(user != null) {
			UserClaims claims = new UserClaims();
			claims.setClaimValue("name", user.getName());
			claims.setClaimValue("surname", user.getSurname());
			claims.setClaimValue("email", user.getEmail());
			claims.setClaimValue("role", user.getRole().toString());
			return jwtHelper.generateJwt(claims);
		} else
			throw new UserNotFoundException();
	}

	@Override
	public User getAuthenticated(String jwt) throws UnauthenticatedException {
		if(!jwtHelper.expired(jwt)) {
			Set<String> claimsFromJwt = new HashSet<String>();
			claimsFromJwt.add("email");
			UserClaims claims = jwtHelper.decodeJwt(jwt, claimsFromJwt);
			User user = userRepository.findByEmail(claims.getClaimValue("email"));
			if(user != null)
				return user;
			else
				throw new UnauthenticatedException();
		} else
			throw new UnauthenticatedException();
	}

	@Override
	public User getAuthorized(String jwt, UserRole role) throws UnauthenticatedException, UnauthorizedException {
		User user = getAuthenticated(jwt);
		if (user.getRole() == role)
			return user;
		else
			throw new UnauthorizedException();
	}
}
