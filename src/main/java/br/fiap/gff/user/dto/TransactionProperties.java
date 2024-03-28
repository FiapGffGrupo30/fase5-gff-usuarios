package br.fiap.gff.user.dto;

import br.fiap.gff.user.models.Wallet;
import lombok.Builder;

@Builder
public record TransactionProperties(Long customerId, Wallet paymentMethod) {

}
