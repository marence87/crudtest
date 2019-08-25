package com.marko.springboot.crudtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marko.springboot.crudtest.dao.StudentDAOHibernateImpl;
import com.marko.springboot.crudtest.entity.Student;

@Service
public class StudentServiceImpl implements ServiceInterface<Student>{
	
	private StudentDAOHibernateImpl studentDAO;

	@Autowired
	public StudentServiceImpl(StudentDAOHibernateImpl studDAO) {
		studentDAO = studDAO;
		
	}
	
	public List<Student> sortByName(List<Student> listStudent) {

		for (int i = 0; i < listStudent.size() - 1; i++) {
			for (int j = 0; j < listStudent.size() - i - 1; j++) {
				if (compare(listStudent.get(j), listStudent.get(j + 1)) > 0) {
					Student tmp = listStudent.get(j);
					listStudent.set(j, listStudent.get(j + 1));
					listStudent.set(j + 1, tmp);
				}
			}
		}

		return listStudent;
	}

	public int compare(Student student1, Student student2) {

		return (student1.getIme() + " " + student1.getPrezime())
				.compareTo(student2.getIme() + " " + student2.getPrezime());
	}

	@Override
	@Transactional
	public List<Student> findAll() {
		return sortByName(studentDAO.findAll());
	}

	@Override
	@Transactional
	public Student findById(int theId) {
		return studentDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Student student) {
		studentDAO.save(student);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		studentDAO.deleteById(theId);
		
	}
	

}
