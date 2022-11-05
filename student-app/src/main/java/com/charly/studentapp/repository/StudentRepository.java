package com.charly.studentapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charly.studentapp.entity.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	
	//	go to docs.spring.io 'website' and go to learn under spring data jpa
	//	they are strict naming conventions that you follow like these
	//	to allow these methods below to auto-implemented
	
	public List<Student> findByFirstName(String firstName);
	
	public List<Student> findByFirstNameContaining(String firstName);
	
	public List<Student> findByLastNameNotNull();
		
	public Student findByFirstNameAndLastName(String firstName, String lastName);
	
	
	
	//	It comes when normal naming does not do all we need; we can make
	//	custom @Query using JPQL language; its like sql but it uses java 
	//	syntax classname not tablename instead
	//JPQL
	@Query("select s from Student s where s.emailId = ?1")
	public Student getStudentByEmailAddress(String emailId);
	
	
	//JPQL
	@Query("select s.firstName from Student s where s.emailId = ?1")
	public String getStudentFirstNameByEmailAddress(String emailId);
	
	
	//	we could also do NativeQuery which is Native SQL not pure SQL
	//Native
	@Query(value="SELECT * FROM tbl_student s WHERE s.email_address = ?1",nativeQuery=true)
	public Student getStudentByEmailAddressNative(String emailId);
	
	
	//Native-with-Named Param
	@Query(value="SELECT * FROM tbl_student s WHERE s.email_address = :emailId",nativeQuery=true)
	public Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);
	
	
	
	//	a case of a method that will modify our database
	@Modifying
	@Transactional		//each edit calls a transaction
	@Query(value="UPDATE tbl_student set first_name = ?1 WHERE email_address = ?2",nativeQuery=true)
	public int updateStudentNameByEmailId(String firstName, String emailId);
}

