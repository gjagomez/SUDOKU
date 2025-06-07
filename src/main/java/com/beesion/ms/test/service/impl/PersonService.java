package com.beesion.ms.test.service.impl;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.repository.PersonRepo;
import com.beesion.ms.test.service.IPersonService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PersonService implements IPersonService{

	//@Inject 
	PersonRepo personRepo = new PersonRepo();
	
	@Inject 
	PersonRepo personRepos;
	
	@Override
	public void save(Person per) {
		personRepos.save(per);
	}
	
	

}
