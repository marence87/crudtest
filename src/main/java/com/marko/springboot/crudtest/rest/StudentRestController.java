package com.marko.springboot.crudtest.rest;

import java.util.ArrayList;
import java.util.List;


import com.marko.springboot.crudtest.service.StudentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marko.springboot.crudtest.entity.Student;
import com.marko.springboot.crudtest.entity.Fakultet;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private StudentServiceImpl studentService;

	@Autowired
	public StudentRestController(StudentServiceImpl theStudentService) {
		studentService = theStudentService;
	}

	@GetMapping("/studenti")
	public List<Student> findAll() {
		List<Student> studenti = studentService.findAll();
		
		for (Student studenti1 : studenti) {
			studenti1.getFakulteti().clear();

		}
		
		return studenti;
	}

	@GetMapping("/studenti/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		Student student = studentService.findById(studentId);

		if (student == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}
		
		List<Fakultet> fakulteti = student.getFakulteti();
		
		for (Fakultet fakulteti1 : fakulteti) {
			fakulteti1.getStudenti().clear();
		}
		
		return student;
	}
	
	@GetMapping("/studenti/jmbg/{studentJMBG}")
	public Student getStudentByJmbg(@PathVariable String studentJMBG) {

		List<Student> studenti = studentService.findAll();
		int id = 0;
		
		for (Student studenti1 : studenti) {
			if (studentJMBG.equals(studenti1.getJmbg())) {
				id = studenti1.getId();
			}
		}
		
		Student student = studentService.findById(id);
		student.getFakulteti().clear();

		return student;
	}
	

	@GetMapping("/studenti/visefakulteta")
	public List<Student> getStudentViseFakulteta() {

		List<Student> studenti = studentService.findAll();
		List<Student> studenti2 = new ArrayList<>();
		
		for (Student studenti1 : studenti) {
			if (studenti1.getFakulteti().size() > 1) {
				studenti2.add(studenti1);
			}
		}
		
		for (Student student2 : studenti2) {
			student2.getFakulteti().clear();
		}

		return studenti2;
	}
	
	
	@PostMapping("/studenti")
	public Student addStudent(@RequestBody Student student) {
		student.setId(0);
		studentService.save(student);
		
		return student;
	}

	@PutMapping("/studenti")
	public Student updateStudent(@RequestBody Student student) {
		studentService.save(student);
		
		return student;
	}

	@DeleteMapping("/studenti/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {
		Student student = studentService.findById(studentId);
		
		if (student == null) {
			throw new RuntimeException("Student sa id nije pronadjen - " + studentId);
		}

		studentService.deleteById(studentId);

		return "Obrisan je student sa id - " + studentId;
	}

	
}
