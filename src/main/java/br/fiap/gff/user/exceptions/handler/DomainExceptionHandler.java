package br.fiap.gff.user.exceptions.handler;

import br.fiap.gff.user.dto.ErrorResponse;
import br.fiap.gff.user.exceptions.base.DomainException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DomainExceptionHandler implements ExceptionMapper<DomainException> {
    @Override
    public Response toResponse(DomainException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        switch (e.getErrorType()) {
            case NOT_FOUND -> response.setStatus(Response.Status.NOT_FOUND);
            case BAD_REQUEST -> response.setStatus(Response.Status.BAD_REQUEST);
            case FORBIDDEN -> response.setStatus(Response.Status.FORBIDDEN);
            case UNAUTHORIZED -> response.setStatus(Response.Status.UNAUTHORIZED);
            case INTERNAL_SERVER_ERROR -> response.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
            default -> response.setStatus(Response.Status.BAD_GATEWAY);
        }
        response.setStatusCode(response.getStatus().getStatusCode());
        return Response.status(response.getStatus()).entity(response).build();
    }
}
