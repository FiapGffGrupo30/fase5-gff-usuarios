package br.fiap.gff.user.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private String productId;
    private Integer quantity;
}
