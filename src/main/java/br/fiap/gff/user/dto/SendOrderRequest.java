package br.fiap.gff.user.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@RegisterForReflection
public class SendOrderRequest {

    private Long customerId;
    private UUID correlationalId;
    private ItemRequest[] items;
}
