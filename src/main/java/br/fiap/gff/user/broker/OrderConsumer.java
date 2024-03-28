package br.fiap.gff.user.broker;

import br.fiap.gff.user.dto.OrderUpdateRequest;
import br.fiap.gff.user.usecases.OrderUseCase;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderUseCase order;

    @Incoming("update-orders")
    public void receiveOrder(JsonObject message) {
        OrderUpdateRequest response = message.mapTo(OrderUpdateRequest.class);
        order.updateStatusByCorrelationalId(response.getCorrelationalId(), response.getStatus());
    }

}
