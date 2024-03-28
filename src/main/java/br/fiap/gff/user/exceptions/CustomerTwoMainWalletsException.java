package br.fiap.gff.user.exceptions;

import br.fiap.gff.user.exceptions.base.DomainException;

public class CustomerTwoMainWalletsException extends DomainException {
    public CustomerTwoMainWalletsException(Long id) {
        super(String.format("Customer %s has two main wallets. Select only one main payment method.", id));
    }
}
