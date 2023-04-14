package com.siddu.webapp.mapper;

import com.siddu.webapp.dto.UserDto;
import com.siddu.webapp.entity.User;

public class UserMapper {
	// convert JPA Entity type to UserDto type
	public static UserDto mapToUserDto(User user) {

		UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
		return userDto;

	}

	// convert UserDto type to JPA Entity type
	public static User mapToUser(UserDto userDto) {
		User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
		return user;

	}
}
