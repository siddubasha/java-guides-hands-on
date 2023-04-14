package com.siddu.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.siddu.webapp.entity.Student;

@RestController
public class StudentController {

	// HTTP GET REQUEST
	// http://localhost:8081/student
	@GetMapping("student")
	public ResponseEntity<Student> getStudent() {

		Student student = new Student(25, "siddu", "basha");
		//return new ResponseEntity<Student>(student, HttpStatus.OK);
		return ResponseEntity.ok(student);
	}
	/*without response entity
	 * public Student getStudent() {

		Student student = new Student(25, "siddu", "basha");
		return student;
	}
	 * 
	 */

	// HTTP GET REQUEST
	// http://localhost:8081/students
	@GetMapping("students")
	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();
		students.add(new Student(2, "raj", "pal"));
		students.add(new Student(8, "ram", "charan"));
		students.add(new Student(10, "java", "Oracle"));
		return students;
	}
    
	// Spring boot REST API with path variable
	// HTTP GET REQUEST
	// http://localhost:8081/student/8/sid/bas
	@GetMapping("student/{id}/{first-name}/{last-name}")
	public Student studentPathVariable(@PathVariable("id") int id, @PathVariable("first-name") String firstName,
			@PathVariable("last-name") String lastName) {
		return new Student(id, firstName, lastName);
	}
	
	// Spring boot REST API with request parameters
	// HTTP GET REQUEST
	// http://localhost:8081/student/query?id=90&firstName=siddu&lastName=basha
	@GetMapping("student/query")
	public Student studentReqparam(@RequestParam("id") int i,String firstName,String lastName) {
		return new Student(i, firstName, lastName);
	}
	
	// Spring boot REST API that handles the HTTP POST REQUEST - creating new resource
	// http://localhost:8081/student/create
	@PostMapping("student/create")  // status: 201 created
	@ResponseStatus(HttpStatus.CREATED) // It is used to give http status in respone for REST API'S or we can use Response entity 
	public Student createStudent(@RequestBody Student student) {
		// Requestbody is used to convert JSON object into Java Object
		System.out.println(student.getId()+ " "+student.getFirstName()+" "+student.getLastName());
		return student;
	}
	
	// Spring boot REST API that handles the HTTP PUT REQUEST - updating existed resource
	// http://localhost:8081/student/4/update
	@PutMapping("student/{id}/update")// by default http staus in response(also for update operation) is 200 ok
	public Student updateStudent(@RequestBody Student student,@PathVariable("id") int id) {
		System.out.println(student.getFirstName()+" "+student.getLastName());
		return student;
	}
	
	// Spring boot REST API that handles the HTTP DELETE REQUEST - deleting existed resource
	// http://localhost:8081/student/4/delete
	@DeleteMapping("student/{id}/delete")
	public String deleteStudent(@PathVariable("id") int id) {
		System.out.println(id);
		return "Student deleted sucessfulyy!";
	}
}
