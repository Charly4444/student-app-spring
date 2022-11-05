package com.charly.studentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.charly.studentapp.entity.Student;
import com.charly.studentapp.error.StudentNotFoundException;
import com.charly.studentapp.service.StudentService;


@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	
	
	@PostMapping("/students/add")
	public Student addAStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentService.getStudents();
	}
	
	
	@GetMapping("/students/{id}")
	public Student getOneStudent(@PathVariable ("id") Long id) throws StudentNotFoundException {
		return studentService.getAStudent(id);
	}
	
	@DeleteMapping("/students/{id}")
	public String removeAStudent(@PathVariable ("id") Long id) throws StudentNotFoundException {
		return studentService.removeStudentById(id);
	}
	
	
	@PutMapping("students/update/{id}")
	public Student updateStudentRecord(@RequestBody Student student, @PathVariable Long id) throws StudentNotFoundException{
		return studentService.updateStudentById(id,student);
	}
}
