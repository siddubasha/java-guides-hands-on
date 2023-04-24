package com.siddu.todo.service;

import java.util.List;

import com.siddu.todo.dto.TodoDto;

public interface TodoService {
  
	
	TodoDto addTodo(TodoDto todoDto);
	TodoDto getTodo(Long todoId);
	List<TodoDto> getAllTodos();
	TodoDto updateTodo(TodoDto todoDto);
	void deleteTodo(Long todoId);
	TodoDto completeTodo(Long todoId);
	TodoDto inCompleteTodo(Long todoId);
}
