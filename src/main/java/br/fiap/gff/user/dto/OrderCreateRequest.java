package br.fiap.gff.user.dto;

import java.util.UUID;

public record OrderCreateRequest(Long customerId, UUID transactionId, OrderItemRequest[] items) {

}