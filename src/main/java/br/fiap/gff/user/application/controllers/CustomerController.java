package br.fiap.gff.user.application.controllers;

import br.fiap.gff.user.dto.CustomerCreateRequest;
import br.fiap.gff.user.dto.OrderCreateRequest;
import br.fiap.gff.user.dto.WalletInsertRequest;
import br.fiap.gff.user.models.Customer;
import br.fiap.gff.user.models.Identity;
import br.fiap.gff.user.models.Order;
import br.fiap.gff.user.models.Wallet;
import br.fiap.gff.user.usecases.CustomerUseCase;
import br.fiap.gff.user.usecases.IdentityUseCase;
import br.fiap.gff.user.usecases.OrderUseCase;
import br.fiap.gff.user.usecases.TransactionUseCase;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customer;
    private final IdentityUseCase identity;
    private final OrderUseCase order;
    private final TransactionUseCase transaction;

    @POST
    @Path("/signIn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signIn(CustomerCreateRequest request) {
        Customer c = customer.save(request.toCustomer());
        Identity u = identity.save(request.toIdentity(c));
        return Response.created(URI.create("/customer/" + c.getId())).entity(u).build();
    }

    @GET
    @Path("/login")
    @RolesAllowed("CUSTOMER")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Context SecurityContext ctx) {
        String username = ctx.getUserPrincipal().getName();
        Customer c = identity.getCustomerByUsername(username);
        Wallet mainWallet = c.getWallets().stream().filter(Wallet::getMain).findFirst().orElse(null);
        UUID transactionId = transaction.create(c.getId(), mainWallet);
        Log.info(String.format("User %s logged in - Transaction ID: %s", username, transactionId));
        return Response.ok(transactionId.toString()).build();
    }

    @GET
    @Path("/{id}/orders")
    @RolesAllowed("CUSTOMER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByCustomerId(@PathParam("id") Long id) {
        List<Order> orders = order.filterAllByCustomerId(id);
        return orders.isEmpty() ? Response.noContent().build()
                : Response.ok(orders).build();
    }

    @GET
    @Path("/{id}/orders/completed")
    @RolesAllowed("CUSTOMER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersCompletedByCustomerId(@PathParam("id") Long id) {
        List<Order> orders = order.filterAllByCustomerIdAndStatus(id, "completed");
        return orders.isEmpty() ? Response.noContent().build()
                : Response.ok(orders).build();
    }

    @POST
    @Path("/{id}/orders")
    @RolesAllowed("CUSTOMER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendOrder(@PathParam("id") Long id, OrderCreateRequest input) {
        transaction.verify(id, input.transactionId());
        Customer c = customer.getById(input.customerId());
        order.create(input, c);
        return Response.created(URI.create("/customer/" + c.getId() + "/orders/" + input.transactionId())).build();
    }

    @POST
    @Path("/{id}/wallets")
    @RolesAllowed("CUSTOMER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertWallet(@PathParam("id") Long id, WalletInsertRequest request) {
        Wallet wallet = request.toModel();
        customer.insertWallet(id, wallet);
        return Response.ok(wallet).build();
    }

    @DELETE
    @Path("/{id}/deleteMe")
    @RolesAllowed("CUSTOMER")
    public Response deleteById(@PathParam("id") Long id) {
        identity.deleteByCustomerId(id);
        order.deleleByUserId(id);
        customer.deleteById(id);
        identity.deleteByCustomerId(id);
        return Response.noContent().build();
    }

}
