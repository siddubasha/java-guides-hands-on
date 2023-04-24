package com.siddu.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddu.todo.dto.TodoDto;
import com.siddu.todo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
public class TodoController {

	private TodoService todoService;

	// Build addTodo REST API
	// HTTP POST REQUEST
	// http://localhost:8081/api/todos
	@PreAuthorize("hasRole('ADMIN')")  // only admin  can access this REST API
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		TodoDto savedTodoDto = todoService.addTodo(todoDto);
		return new ResponseEntity<TodoDto>(savedTodoDto, HttpStatus.CREATED);

	}

	// Build getTodo REST API
	// HTTP GET REQUEST
	// http://localhost:8081/api/todos/2
	@PreAuthorize("hasAnyRole('ADMIN','USER')") // admin & user roles can access this REST API
	@GetMapping("/{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
		TodoDto todoDto = todoService.getTodo(todoId);
		return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
	}

	// Build get all todos REST API
	// HTTP GET REQUEST
	// http://localhost:8081/api/todos
	@PreAuthorize("hasAnyRole('ADMIN','USER')") // admin & user roles can access this REST API
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos() {
		List<TodoDto> todoDtos = todoService.getAllTodos();
		return new ResponseEntity<List<TodoDto>>(todoDtos, HttpStatus.OK);

	}

	// Build update todo REST API
	// HTTP PUT REQUSET
	// http://localhost:8081/api/todos/2
	@PreAuthorize("hasRole('ADMIN')")  // only admin user can access this REST API
	@PutMapping("/{id}") // put is used to update existing resource
	public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto) {
		todoDto.setId(todoId);
		TodoDto updatedTodoDto = todoService.updateTodo(todoDto);
		return new ResponseEntity<TodoDto>(updatedTodoDto, HttpStatus.ACCEPTED);

	}

	// Build delete todo REST API
	// HTTP DELETE REQUEST
	// http://localhost:8081/api/todos/2
	@PreAuthorize("hasRole('ADMIN')")  // only admin user can access this REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
		todoService.deleteTodo(todoId);
		return new ResponseEntity<String>("Todo is deleted", HttpStatus.OK);

	}

	// Build complete todo REST API
	// HTTP PUT REQUEST
	// http://localhost:8081/api/todos/2/complete
	@PreAuthorize("hasAnyRole('ADMIN','USER')") // admin & user roles can access this REST API
	@PatchMapping("/{id}/complete") // patch is used to update existing resource partially
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
		TodoDto updatedTodo = todoService.completeTodo(todoId);
		return new ResponseEntity<TodoDto>(updatedTodo, HttpStatus.ACCEPTED);

	}

	// Build inComplete todo REST API
	// HTTP PUT REQUEST
	// http://localhost:8081/api/todos/2/complete
	@PreAuthorize("hasAnyRole('ADMIN','USER')") // admin & user roles can access this REST API
	@PatchMapping("/{id}/incomplete") // patch is used to update existing resource partially
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId) {
		TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
		return new ResponseEntity<TodoDto>(updatedTodo, HttpStatus.ACCEPTED);

	}
}
