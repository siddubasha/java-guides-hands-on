package com.siddu.todo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
     
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest web){
		ErrorDetails error= new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				web.getDescription(false),
				"Todo not found"
				);
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
		
	}
}
