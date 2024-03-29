package br.fiap.gff.user.application.events;

import br.fiap.gff.user.dto.TransactionEvent;
import br.fiap.gff.user.usecases.CustomerUseCase;
import br.fiap.gff.user.usecases.OrderUseCase;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@RequiredArgsConstructor
public class CustomerNotifyEvent {

    private final OrderUseCase order;
    private final CustomerUseCase customer;

    @Incoming("notify")
    @Blocking
    public void handle(JsonObject message) {
        TransactionEvent event = message.mapTo(TransactionEvent.class);
        order.updateStatusByTransactionId(event.getTransactionId(), event.getStatus());
        String notificationMessage = String.format("Order %s status updated to %s",
                event.getTransactionId(), event.getStatus());
        customer.sendNotification(event.getCustomerId(), notificationMessage);
    }

}
