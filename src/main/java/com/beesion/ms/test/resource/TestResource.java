/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beesion.ms.test.resource;

import java.util.Optional;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

/**
 *
 * @author wcordova
 */
@Path("/HelloWord")
public class TestResource {

    @GET
    public String helloWord() {
        return "Hello word!";
    }
    
    @GET
    @Path("/subHello")
    public String holaMundo() {
        return "Hola mundo!";
    }
    
    @POST
    @Path("/saludar")
    public String saludar(@QueryParam("mensaje") String mensaje) {
    	return Optional.ofNullable(mensaje)
    			.map(n -> "> " + n)
    			.orElse("No se que decir");
    }
    
    @PUT
    @Path("/modificar")
    public String modifcar(@QueryParam("mensaje") String mensaje) {
    	return Optional.ofNullable(mensaje)
    			.map(n -> "> " + n)
    			.orElse("No se que decir");
    }

}
