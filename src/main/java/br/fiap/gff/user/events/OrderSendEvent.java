package br.fiap.gff.user.events;

import br.fiap.gff.user.dto.OrderCreateRequest;
import br.fiap.gff.user.dto.OrderSendRequest;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

@ApplicationScoped
public class OrderSendEvent {

    @Channel("create-event")
    Emitter<OrderSendRequest> createOrderEmitter;

    public void send(OrderCreateRequest request, UUID correlationalId) {
        OrderSendRequest orderSendRequest = OrderSendRequest.builder()
                .transactionId(correlationalId)
                .customerId(request.getCustomerId())
                .items(request.getItems())
                .build();
        createOrderEmitter.send(orderSendRequest);
    }
}
