package sbnz.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sbnz.app.dto.NewUserDTO;
import sbnz.app.dto.UserCredentialsDTO;
import sbnz.app.exception.UserAlreadyExistsException;
import sbnz.app.exception.UserNotFoundException;
import sbnz.app.mapper.UserMapper;
import sbnz.app.model.UserRole;
import sbnz.app.service.AuthService;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

	private AuthService authService;
	private UserMapper userMapper;
	
	@Autowired
	public AuthController(AuthService authService, UserMapper userMapper) {
		this.authService = authService;
		this.userMapper = userMapper;
	}
	
	@PostMapping(value = "register")
	public void register(@RequestBody NewUserDTO dto) {
		try {
			authService.register(userMapper.NewUserDTOToUser(dto, UserRole.BASIC_USER));
		} catch (UserAlreadyExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with provided email already exists");
		}
	}
	
	@PostMapping(value = "logIn")
	public String logIn(@RequestBody UserCredentialsDTO dto) {
		try {
			return authService.logIn(dto.getEmail(), dto.getPassword());
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with provided credentials doesn't exist");
		}
	}
}
