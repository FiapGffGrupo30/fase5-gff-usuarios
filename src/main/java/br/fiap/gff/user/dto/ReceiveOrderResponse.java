package br.fiap.gff.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveOrderResponse {

    private Long customerId;
    private UUID correlationalId;
    private String status;

}
