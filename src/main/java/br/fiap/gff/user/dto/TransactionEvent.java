package br.fiap.gff.user.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@RegisterForReflection
public class TransactionEvent {

    Long customerId;
    UUID transactionId;
    OrderItemRequest[] items;
    String status;
}
