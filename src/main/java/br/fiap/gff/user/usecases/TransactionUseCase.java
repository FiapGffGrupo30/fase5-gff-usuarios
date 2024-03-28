package br.fiap.gff.user.usecases;

import br.fiap.gff.user.models.Wallet;

import java.util.UUID;

public interface TransactionUseCase {
    UUID create(Long customerId, Wallet paymentMethod);

}
