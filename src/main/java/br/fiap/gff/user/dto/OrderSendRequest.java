package br.fiap.gff.user.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@RegisterForReflection
public class OrderSendRequest {

    private Long customerId;
    private UUID correlationalId;
    private OrderItemRequest[] items;
}
