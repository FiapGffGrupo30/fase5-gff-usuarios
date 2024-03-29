package br.fiap.gff.user.application.events;

import br.fiap.gff.user.dto.OrderCreateRequest;
import br.fiap.gff.user.dto.TransactionEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class OrderSendEvent {

    @Channel("order")
    Emitter<TransactionEvent> eventEmitter;

    public void send(OrderCreateRequest request) {
        TransactionEvent transactionEvent = TransactionEvent.builder()
                .transactionId(request.transactionId())
                .customerId(request.customerId())
                .items(request.items())
                .status("PENDING")
                .build();
        eventEmitter.send(transactionEvent);
    }
}
