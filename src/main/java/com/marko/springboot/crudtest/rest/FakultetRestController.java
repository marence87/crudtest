package com.marko.springboot.crudtest.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marko.springboot.crudtest.entity.Fakultet;
import com.marko.springboot.crudtest.entity.Student;
import com.marko.springboot.crudtest.service.FakultetServiceImpl;
import com.marko.springboot.crudtest.service.StudentServiceImpl;

@RestController
@RequestMapping("/api")
public class FakultetRestController {
	
	private FakultetServiceImpl fakultetService;
	private StudentServiceImpl studentService;
	
	public FakultetRestController (FakultetServiceImpl fakultetService, StudentServiceImpl studService) {
		this.fakultetService = fakultetService;
		studentService=studService;

		
	}
	
	@Autowired
	@GetMapping("/fakulteti")
	public List<Fakultet> findAll(){
		
		List<Fakultet> fakulteti = fakultetService.findAll();
		
		return fakulteti;

	}
	
	
	@GetMapping("/fakulteti/{fakultetId}")
	public Fakultet getFakultet(@PathVariable int fakultetId) {
		
		Fakultet fakultet = fakultetService.findById(fakultetId);
		
		if (fakultet == null) {
			throw new RuntimeException("Nije pronadjen fakultet sa id: "+ fakultetId);
		}
		
		return fakultet;
	}
	
	
	@PostMapping("/fakulteti")
	public Fakultet addFakultet(@RequestBody Fakultet fakultet) {
		fakultet.setId(0);
		fakultetService.save(fakultet);
		
		return fakultet;
	}

	@PutMapping("/fakulteti")
	public Fakultet updateFakultet(@RequestBody Fakultet fakultet) {
		fakultetService.save(fakultet);
		
		return fakultet;
	}

	@DeleteMapping("/fakulteti/{fakultetId}")
	public String deleteFakultet(@PathVariable int fakultetId) {
		Fakultet fakultet = fakultetService.findById(fakultetId);
		
		if (fakultet == null) {
			throw new RuntimeException("Fakultet id not found - " + fakultetId);
		}

		fakultetService.deleteById(fakultetId);

		return "Deleted fakultet id - " + fakultetId;

	}
	
	@PutMapping("/fakulteti/{fakultetId}/studenti/{studentId}")
	public String addStudentToFakultet(@PathVariable int fakultetId, @PathVariable int studentId) {
		Fakultet fakultet = fakultetService.findById(fakultetId);
		Student student = studentService.findById(studentId);
		fakultet.addStudent(student);
		student.addFakultet(fakultet);
		fakultetService.save(fakultet);
		studentService.save(student);
		
		return "Student id - " + studentId + " is added to Fakultet id - " + fakultetId;
	}

	@DeleteMapping("/fakulteti/{fakultetId}/studenti/{studentId}")
	public String deleteStudentFromFakultet(@PathVariable int fakultetId, @PathVariable int studentId) {
		Fakultet fakultet = fakultetService.findById(fakultetId);
		Student student = studentService.findById(studentId);
		
		if (fakultet == null || student == null) {
			throw new RuntimeException("Fakultet or Student id not found");
		}

		fakultet.deleteStudent(student);
		student.deleteFakultet(fakultet);
		fakultetService.save(fakultet);
		studentService.save(student);

		return "Deleted";
	}
	

}
