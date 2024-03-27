package br.fiap.gff.user.broker;

import br.fiap.gff.user.dto.CreateOrderRequest;
import br.fiap.gff.user.dto.SendOrderRequest;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

@ApplicationScoped
public class OrderProducer {

    @Channel("send-order")
    Emitter<SendOrderRequest> sendOrderEmmiter;

    public void sendOrder(CreateOrderRequest request, UUID correlationalId) {
        SendOrderRequest sendOrderRequest = SendOrderRequest.builder()
                .correlationalId(correlationalId)
                .customerId(request.getCustomerId())
                .items(request.getItems())
                .build();
        sendOrderEmmiter.send(sendOrderRequest);
    }
}
