package com.marko.springboot.crudtest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marko.springboot.crudtest.entity.Fakultet;

@Repository
public class FakultetDAOHibernateImpl implements DAO<Fakultet> {
	
	private EntityManager entityManager;
	
	@Autowired
	public FakultetDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	

	@Override
	@Transactional
	public List<Fakultet> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Fakultet> theQuery = currentSession.createQuery("from Fakultet", Fakultet.class);
		
		List<Fakultet> fakulteti = theQuery.getResultList();
		
		return fakulteti;
	}


	@Override
	@Transactional
	public Fakultet findById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
	
		Fakultet fakultet = currentSession.get(Fakultet.class,theId);
		
		return fakultet;
	}


	@Override
	@Transactional
	public void save(Fakultet fakultet) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(fakultet);
		
	}


	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Fakultet where id=:fakultetid");
		
		theQuery.setParameter("fakultetId", theId);
		
		theQuery.executeUpdate();
		
	}

}
