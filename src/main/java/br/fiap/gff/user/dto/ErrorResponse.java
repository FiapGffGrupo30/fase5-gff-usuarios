package br.fiap.gff.user.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private Integer statusCode;

}
