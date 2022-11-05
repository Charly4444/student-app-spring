package com.charly.studentapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charly.studentapp.entity.Student;
import com.charly.studentapp.error.StudentNotFoundException;
import com.charly.studentapp.repository.StudentRepository;



@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
		
	
	

	
	
	//ADD A STUDENT
	@Override
	public Student addStudent(Student student) {
		studentRepository.save(student);
		return student;
	}

	
	//GET ALL STUDENTS
	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	
	//GET A STUDENT
	@Override
	public Student getAStudent(Long id) throws StudentNotFoundException{
		if (studentRepository.findById(id).isEmpty()) {
			throw new StudentNotFoundException("Student Not Found!!");
		}
		return studentRepository.findById(id).get();
		
	}
	
	
	//DELETE STUDENT BY ID
	@Override
	public String removeStudentById(Long id) throws StudentNotFoundException{
		if (studentRepository.findById(id).isEmpty()) {
			throw new StudentNotFoundException("Student Not Found!!");
		}
		studentRepository.deleteById(id);
		return "You have successfully Deleted a User";
		
	}

	
	
	//UPDATE A STUDENT
	@Override
	public Student updateStudentById(Long id,
			com.charly.studentapp.entity.Student student) throws StudentNotFoundException{
		
		if (studentRepository.findById(id).isEmpty()) {
			throw new StudentNotFoundException("Student Not Found!!");
		}
		//if the student is then found
		Student target_student = studentRepository.findById(id).get();
		target_student.setFirstName(student.getFirstName());
		target_student.setLastName(student.getLastName());
		target_student.setEmailId(student.getEmailId());
		studentRepository.save(target_student);		//and save
		
		return target_student;
		
	}


}
