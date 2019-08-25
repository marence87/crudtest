package com.marko.springboot.crudtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marko.springboot.crudtest.dao.FakultetDAOHibernateImpl;
import com.marko.springboot.crudtest.entity.Fakultet;

@Service
public class FakultetServiceImpl implements ServiceInterface<Fakultet>{
	
	private FakultetDAOHibernateImpl fakultetDAO;
	
	@Autowired
	public FakultetServiceImpl(FakultetDAOHibernateImpl fakultDAO) {
		fakultetDAO = fakultDAO;
		
	}
	
	public List<Fakultet> sortByName(List<Fakultet> listFakultet) {

		for (int i = 0; i < listFakultet.size() - 1; i++) {
			for (int j = 0; j < listFakultet.size() - i - 1; j++) {
				if (compare(listFakultet.get(j), listFakultet.get(j + 1)) > 0) {
					Fakultet tmp = listFakultet.get(j);
					listFakultet.set(j, listFakultet.get(j + 1));
					listFakultet.set(j + 1, tmp);
				}
			}
		}

		return listFakultet;
	}

	public int compare(Fakultet fakultet1, Fakultet fakultet2) {

		return (fakultet1.getNaziv()).compareTo(fakultet2.getNaziv());
	}

	@Override
	public List<Fakultet> findAll() {
		return sortByName(fakultetDAO.findAll());
	}

	@Override
	@Transactional
	public Fakultet findById(int theId) {
		return fakultetDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Fakultet fakultet) {
		fakultetDAO.save(fakultet);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		fakultetDAO.deleteById(theId);
		
	}
}
