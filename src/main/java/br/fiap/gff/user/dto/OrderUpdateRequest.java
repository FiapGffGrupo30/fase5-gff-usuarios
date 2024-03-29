package br.fiap.gff.user.dto;

import java.util.UUID;

public record OrderUpdateRequest(Long customerId, UUID correlationalId, String status) {

}
