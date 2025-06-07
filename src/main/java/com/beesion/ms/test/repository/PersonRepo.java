package com.beesion.ms.test.repository;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.repository.impl.IPersonRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PersonRepo implements IPersonRepo {

	@Inject
	EntityManager em;

	@Override
	@Transactional
	public void save(Person per) {
		em.persist(per);
	}

}
