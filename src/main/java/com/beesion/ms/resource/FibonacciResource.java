package com.beesion.ms.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/fibonacci")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FibonacciResource {

    /**
     * Genera una secuencia de Fibonacci con parámetros iniciales y cantidad
     * @param request Objeto con los parámetros de la secuencia
     * @return Response con la secuencia generada
     */
    @POST
    @Path("/generate")
    public Response generateFibonacci(FibonacciRequest request) {
        try {
            if (request == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Request body is required"))
                        .build();
            }

            List<Integer> sequence = fibonacci(request.initialNumbers, request.n);

            return Response.ok()
                    .entity(new FibonacciResponse(sequence))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Error generating Fibonacci sequence: " + e.getMessage()))
                    .build();
        }
    }


    @GET
    @Path("/simple")
    public Response generateSimpleFibonacci(
            @QueryParam("first") @DefaultValue("0") int first,
            @QueryParam("second") @DefaultValue("1") int second,
            @QueryParam("count") @DefaultValue("10") int count) {

        try {
            int[] initialNumbers = {first, second};
            List<Integer> sequence = fibonacci(initialNumbers, count);

            return Response.ok()
                    .entity(new FibonacciResponse(sequence))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Error generating Fibonacci sequence: " + e.getMessage()))
                    .build();
        }
    }

    /**
     * Implementa la secuencia de Fibonacci según las especificaciones
     * Aplica principio Open-Close: abierto para extensión, cerrado para modificación
     *
     * @param initialNumbers Arreglo con los números iniciales predeterminados
     * @param n Número de elementos que deben devolver las funciones
     * @return Lista con la secuencia de Fibonacci generada
     */
    public List<Integer> fibonacci(int[] initialNumbers, int n) {
        // Validaciones de entrada
        if (initialNumbers == null || initialNumbers.length < 2) {
            throw new IllegalArgumentException("Se requieren al menos 2 números iniciales");
        }

        if (n < 0) {
            throw new IllegalArgumentException("El número de elementos no puede ser negativo");
        }

        // Caso especial: si n = 0, devolver lista vacía
        if (n == 0) {
            return new ArrayList<>();
        }

        List<Integer> sequence = new ArrayList<>();

        // Agregar los números iniciales según el valor de n
        if (n >= 1) {
            sequence.add(initialNumbers[0]);
        }
        if (n >= 2) {
            sequence.add(initialNumbers[1]);
        }

        // Generar el resto de la secuencia
        for (int i = 2; i < n; i++) {
            int nextNumber = sequence.get(i - 1) + sequence.get(i - 2);
            sequence.add(nextNumber);
        }

        return sequence;
    }

    // Clases para las peticiones y respuestas JSON
    public static class FibonacciRequest {
        public int[] initialNumbers;
        public int n;

        // Constructor por defecto necesario para deserialización JSON
        public FibonacciRequest() {}

        public FibonacciRequest(int[] initialNumbers, int n) {
            this.initialNumbers = initialNumbers;
            this.n = n;
        }
    }

    public static class FibonacciResponse {
        public List<Integer> sequence;
        public int count;

        public FibonacciResponse(List<Integer> sequence) {
            this.sequence = sequence;
            this.count = sequence.size();
        }
    }

    public static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}