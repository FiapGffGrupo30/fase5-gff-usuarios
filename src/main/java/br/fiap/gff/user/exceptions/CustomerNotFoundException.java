package br.fiap.gff.user.exceptions;

import java.io.Serial;

public class CustomerNotFoundException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long id) {
        super("Customer of id " + id + " not found!");
    }
}
