package br.fiap.gff.user.dto;

import lombok.Data;

@Data
public class OrderCreateRequest {

    private Long customerId;
    private OrderItemRequest[] items;

}