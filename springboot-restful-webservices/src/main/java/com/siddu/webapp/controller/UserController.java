package com.siddu.webapp.controller;


import java.time.LocalDateTime;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.siddu.webapp.dto.UserDto;
import com.siddu.webapp.exception.ErrorDetails;
import com.siddu.webapp.exception.ResourceNotFoundException;
import com.siddu.webapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(
		name = "CRUD REST API for User Resource",
		description = "CRUD REST API's- Create User,Update User,Get User,Get All Users,Delete User"
		)

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	private UserService userService;

	
	@Operation(
			summary = "Create User REST API",
			description = "Create User REST API is used to save user in database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 CREATED"
			)
	// build create user REST API
	// http://localhost:8081/api/users
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto savedUserDto = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(savedUserDto, HttpStatus.CREATED);
	}
    
	@Operation(
			summary = "Get User by Id REST API",
			description = "Get User by Id REST API is used to get one user from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP STATUS 200 SUCESS"
			)
	// build get user by id REST API
	// http://localhost:8081/api/users/1
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto userDto = userService.getUserById(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@Operation(
			summary = "Get All Users REST API",
			description = "Get All Users REST API is used to get all users from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP STATUS 200 SUCESS"
			)
	// build get all users REST API
	// http://localhost:8081/api/users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {

		List<UserDto> usersList = userService.getAllUsers();

		return new ResponseEntity<List<UserDto>>(usersList, HttpStatus.OK);
	}

	@Operation(
			summary = "Update User REST API",
			description = "Update User REST API is used to update  a particular user in the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP STATUS 200 SUCESS"
			)
	// build update user REST API
	// http://localhost:8081/api/users
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updaterUser(@PathVariable("id") Long userId,@Valid @RequestBody UserDto user) {
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);

	}

	@Operation(
			summary = "Delete User by ID  REST API",
			description = "Delete User by ID REST API is used to delete a particular user in the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP STATUS 200 SUCESS"
			)
	// build delete user REST API
	// http://localhost:8081/api/users/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<String>("user object deleted", HttpStatus.OK);

	}
	
	// it is used to handle specific exception of User resource
/*	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){
		
		ErrorDetails errorDetails= new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"USER_NOT-FOUND"
				
				);
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	} */
}
