package com.beesion.ms.test.resource;

import java.util.List;

import com.beesion.ms.test.dto.Temperatura;
import com.beesion.ms.test.service.impl.TemperaturaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/temperaturas")
public class TemperaturasResource {
	
	@Inject
	private TemperaturaService temperaturas;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Temperatura medicion() {
		return new Temperatura("Guatemala", 25, 32);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public List<Temperatura> list() {
		return temperaturas.obtenerTemperaturas();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Temperatura guardar(Temperatura temp) {
		temperaturas.addTemperatura(temp);
		return temp;
	}
	
	@GET
	@Path("/maxima")
	@Produces(MediaType.TEXT_PLAIN)
	public Response maxima() {
		if(temperaturas.isEmpty())
			return Response.status(400).entity("No hay temperaturas").build();
		int tempetaturaMaxima = temperaturas.maxima();
		return Response.ok(Integer.toString(tempetaturaMaxima)).build();			
	}
	

}
