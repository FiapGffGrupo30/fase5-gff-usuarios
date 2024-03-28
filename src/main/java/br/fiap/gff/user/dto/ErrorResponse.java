package br.fiap.gff.user.dto;

import jakarta.ws.rs.core.Response;
import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private Integer statusCode;
    private Response.Status status;

}
