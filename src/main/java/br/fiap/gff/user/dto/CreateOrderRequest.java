package br.fiap.gff.user.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private Long customerId;
    private ItemRequest[] items;

}