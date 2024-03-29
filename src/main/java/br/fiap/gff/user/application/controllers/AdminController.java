package br.fiap.gff.user.application.controllers;

import br.fiap.gff.user.usecases.CustomerUseCase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CustomerUseCase customer;

    @GET
    @Path("/getCustomer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(Long id) {
        return Response.ok(customer.getById(id)).build();
    }

}
