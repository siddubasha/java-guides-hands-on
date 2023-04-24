package com.siddu.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.siddu.todo.dto.TodoDto;
import com.siddu.todo.entity.Todo;
import com.siddu.todo.exception.ResourceNotFoundException;
import com.siddu.todo.repository.TodoRepository;
import com.siddu.todo.service.TodoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor // constructor based DI
public class TodoServiceImpl implements TodoService {
	private TodoRepository todoRepository;
	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		// convert TodoDto into Todo JPA Entity and then save Todo object in database
		Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo savedTodo = todoRepository.save(todo);
		// convert Todo JPA entity into TodoDto and return to controller layaer
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long todoId) {
		Todo todo = todoRepository.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not found with id:" + todoId));
		TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
		return todoDto;
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		List<TodoDto> todoDtos = todos.stream().map(todo -> modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
		return todoDtos;
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto) {
		Todo existingTodo = todoRepository.findById(todoDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not found with id:" + todoDto.getId()));
		existingTodo.setTitle(todoDto.getTitle());
		existingTodo.setDescription(todoDto.getDescription());
		existingTodo.setCompleted(todoDto.isCompleted());
		Todo updatedTodo = todoRepository.save(existingTodo);
		TodoDto updatedTodoDto = modelMapper.map(updatedTodo, TodoDto.class);
		return updatedTodoDto;
	}

	@Override
	public void deleteTodo(Long todoId) {
		Todo existingTodo = todoRepository.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not found with id:" + todoId));
		todoRepository.delete(existingTodo);
	}

	@Override
	public TodoDto completeTodo(Long todoId) {
		Todo existingTodo = todoRepository.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not found with id:" + todoId));
		existingTodo.setCompleted(Boolean.TRUE);
		Todo updatedTodo = todoRepository.save(existingTodo);
		TodoDto updatedTodoDto = modelMapper.map(updatedTodo, TodoDto.class);
		return updatedTodoDto;
	}

	@Override
	public TodoDto inCompleteTodo(Long todoId) {
		Todo existingTodo = todoRepository.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo is not found with id:" + todoId));
		existingTodo.setCompleted(Boolean.FALSE);
		Todo updatedTodo = todoRepository.save(existingTodo);
		TodoDto updatedTodoDto = modelMapper.map(updatedTodo, TodoDto.class);
		return updatedTodoDto;
	
	}

}
