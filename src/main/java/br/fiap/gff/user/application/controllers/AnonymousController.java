package br.fiap.gff.user.application.controllers;

import br.fiap.gff.user.models.Order;
import br.fiap.gff.user.usecases.CustomerUseCase;
import br.fiap.gff.user.usecases.OrderUseCase;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/anonymous")
@RequiredArgsConstructor
public class AnonymousController {

    private final CustomerUseCase customer;
    private final OrderUseCase order;

    @GET
    @Path("/{nickname}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(String nickname) {
        return Response.ok(customer.createAnonymous(nickname)).build();
    }

    @GET
    @Path("/{id}/orders")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByCustomerId(@PathParam("id") Long id) {
        customer.getById(id);
        List<Order> orders = order.filterAllByCustomerId(id);
        return orders.isEmpty() ? Response.noContent().build()
                : Response.ok(orders).build();
    }

    @GET
    @Path("/{id}/orders/completed")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersCompletedByCustomerId(@PathParam("id") Long id) {
        customer.getById(id);
        List<Order> orders = order.filterAllByCustomerIdAndStatus(id, "completed");
        return orders.isEmpty() ? Response.noContent().build()
                : Response.ok(orders).build();
    }
}
