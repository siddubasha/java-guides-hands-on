package com.siddu.webapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.siddu.webapp.dto.UserDto;
import com.siddu.webapp.entity.User;
import com.siddu.webapp.exception.EmailAlreadyExistException;
import com.siddu.webapp.exception.ResourceNotFoundException;
import com.siddu.webapp.mapper.UserMapper;
import com.siddu.webapp.repository.UserRepository;
import com.siddu.webapp.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
        
	//	User user=UserMapper.mapToUser(userDto);
		Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistException("Email already exist for user");
		}
		
		User user=modelMapper.map(userDto, User.class);
		User savedUser= userRepository.save(user);
	//	UserDto savedUserdto=UserMapper.mapToUserDto(savedUser);
		UserDto savedUserdto=modelMapper.map(savedUser, UserDto.class);
		return savedUserdto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user= userRepository.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User", "Id", userId)
				);
		//return UserMapper.mapToUserDto(user);
		 return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users= userRepository.findAll();
		
	//	return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		return users.stream().map((user)-> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
				()->new ResourceNotFoundException("User", "Id", user.getId())
				);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);
	//	return UserMapper.mapToUserDto(updatedUser);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Long userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User", "Id", userId)
				);
		
		userRepository.deleteById(userId);
	}

}
