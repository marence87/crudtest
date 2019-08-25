package com.marko.springboot.crudtest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marko.springboot.crudtest.entity.Student;

@Repository
public class StudentDAOHibernateImpl implements DAO<Student>{
	
	private EntityManager entityManager;
	
	@Autowired
	public StudentDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public List<Student> findAll() {
Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Student> theQuery = currentSession.createQuery("from Student", Student.class);
		
		List<Student> studenti = theQuery.getResultList();
		
		return studenti;
	}

	@Override
	@Transactional
	public Student findById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
	
		Student student1 = currentSession.get(Student.class,theId);
		
		return student1;
	}


	@Override
	@Transactional
	public void save(Student student) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(student);
		
	}


	@Override
	@Transactional
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Student where id=:studentid");
		
		theQuery.setParameter("studentId", theId);
		
		theQuery.executeUpdate();
		
	}


}
