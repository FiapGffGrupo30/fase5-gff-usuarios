package br.fiap.gff.user.exceptions.base;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    protected ErrorTypes errorType;

    public DomainException(String message) {
        super(message);
    }

}
