package br.fiap.gff.user.exceptions;

import br.fiap.gff.user.exceptions.base.DomainException;
import br.fiap.gff.user.exceptions.base.ErrorTypes;

import java.util.UUID;

public class TransactionNotAllowedException extends DomainException {
    public TransactionNotAllowedException(Long customerId, UUID transactionId) {
        super(String.format("Transaction not allowed for Customer ID: %s, Transaction ID: %s", customerId, transactionId));
        this.errorType = ErrorTypes.UNAUTHORIZED;
    }

}
