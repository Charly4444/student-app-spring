package com.charly.studentapp.service;

import java.util.List;

import com.charly.studentapp.entity.Student;
import com.charly.studentapp.error.StudentNotFoundException;

public interface StudentService {

	
	Student addStudent(Student student);

	List<Student> getStudents();

	String removeStudentById(Long id) throws StudentNotFoundException;

	Student updateStudentById(Long id, Student student) throws StudentNotFoundException;

	Student getAStudent(Long id) throws StudentNotFoundException;



}
