package com.siddu.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
   
	// HTTP GET REQUEST
	// http://localhost:8081/hello
	
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "hello world!";
	}
	
	
}
