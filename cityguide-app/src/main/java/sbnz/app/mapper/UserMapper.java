package sbnz.app.mapper;

import org.springframework.stereotype.Component;

import sbnz.app.dto.NewUserDTO;
import sbnz.app.model.User;
import sbnz.app.model.UserRole;

@Component
public class UserMapper {

	public User NewUserDTOToUser(NewUserDTO dto, UserRole role) {
		return new User(dto.getName(), dto.getSurname(), dto.getEmail(), dto.getPassword(), role);
	}
}
