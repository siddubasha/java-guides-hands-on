package com.siddu.webapp.service;


import java.util.List;

import com.siddu.webapp.dto.UserDto;
import com.siddu.webapp.entity.User;
public interface UserService {
      UserDto createUser(UserDto userDto);
      UserDto getUserById(Long userId);
      List<UserDto> getAllUsers();
      UserDto updateUser(UserDto userDto);
      void deleteUser(Long userId);
}
