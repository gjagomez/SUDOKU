package com.beesion.ms.test.resource;


import com.beesion.ms.test.dto.PolicyDomainDto;
import com.beesion.ms.test.service.IServicePassword;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/password")
public class PasswordResource {
	
	@Inject
	private IServicePassword servicePassword;
	
	@POST
	public Response generatePasswordDto(PolicyDomainDto p) {
		
		try {			
			return Response.ok(servicePassword.generatePassword(p)).build();
		}catch(Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	
	}

}
