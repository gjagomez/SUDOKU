package com.beesion.ms.test.resource;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.dto.PersonDto;
import com.beesion.ms.test.service.impl.PersonService;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/Person")
public class PersonaResource {

	@Inject
	PersonService person;

	@POST
	public Response save(PersonDto per) {
		Person p = new Person();
		p.setName(per.getName());
		person.save(p);
		
		return Response.ok("Elemento guardado").build();
	}

}
