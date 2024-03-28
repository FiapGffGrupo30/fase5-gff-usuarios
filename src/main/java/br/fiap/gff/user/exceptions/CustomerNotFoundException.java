package br.fiap.gff.user.exceptions;

import br.fiap.gff.user.exceptions.base.DomainException;
import br.fiap.gff.user.exceptions.base.ErrorTypes;

import java.io.Serial;

public class CustomerNotFoundException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long id) {
        super("Customer of id " + id + " not found!");
        this.errorType = ErrorTypes.NOT_FOUND;
    }
}
