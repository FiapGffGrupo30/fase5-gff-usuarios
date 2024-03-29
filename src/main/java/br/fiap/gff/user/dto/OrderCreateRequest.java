package br.fiap.gff.user.dto;

public record OrderCreateRequest(Long customerId, OrderItemRequest[] items) {

}