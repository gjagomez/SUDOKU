package com.beesion.ms.test.resource;

import java.util.List;

import com.beesion.ms.model.Book;
import com.beesion.ms.test.dto.BookDto;
import com.beesion.ms.test.repository.BookRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/book")
public class BookResource {
    
    private static final Logger logger = LogManager.getLogger(BookResource.class);
	
	@Inject
	private BookRepository repo;
	
	@GET
	public List<Book> index(){
		return repo.listAll();
	}
	
	@POST
	public Response insert(BookDto b) {
		Book book = new Book();
		book.setNumPages(b.getNumPages());
		book.setPubDate(b.getPubDate());
		book.setDescription(b.getDescription());
		book.setTitle(b.getTitle());
		
		repo.persist(book);
		return Response.ok("Se ha registrado").build();
	}
	
	@GET
	@Path("{id}")
	public Response getBook(@PathParam("id") Long id) {
		var book = repo.findById(id);
		if(book != null) {
			return Response.ok(book).build();
		}else {
			return Response.status(400).entity("No existe ese id").build();
		}
	}
	
	@GET
	@Path("mayorAId/{id}")
	public Response traerMayor(@PathParam("id") Long id) {
		var book = repo.find("numpages > ?1", id).list();
		if(book != null) {
			return Response.ok(book).build();
		}else {
			return Response.status(400).entity("No existe ese id").build();
		}
	}


}
