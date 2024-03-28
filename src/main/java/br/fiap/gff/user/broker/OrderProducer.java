package br.fiap.gff.user.broker;

import br.fiap.gff.user.dto.OrderCreateRequest;
import br.fiap.gff.user.dto.OrderSendRequest;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

@ApplicationScoped
public class OrderProducer {

    @Channel("send-orders")
    Emitter<OrderSendRequest> sendOrderEmmiter;

    public void sendOrder(OrderCreateRequest request, UUID correlationalId) {
        OrderSendRequest orderSendRequest = OrderSendRequest.builder()
                .transactionId(correlationalId)
                .customerId(request.getCustomerId())
                .items(request.getItems())
                .build();
        sendOrderEmmiter.send(orderSendRequest);
    }
}
