package gov.caixa.presentation.exception;

import gov.caixa.domain.exception.ProdutoNaoElegivelException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class GlobalExceptionMapper {

    @Provider
    public static class ProdutoNaoElegivelMapper implements ExceptionMapper<ProdutoNaoElegivelException> {

        @Override
        public Response toResponse(ProdutoNaoElegivelException ex) {
            return Response.status(422)
                    .entity(errorBody(422, ex.getMessage()))
                    .build();
        }
    }

    @Provider
    public static class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

        @Override
        public Response toResponse(ConstraintViolationException ex) {
            String message = ex.getConstraintViolations().stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));
            return Response.status(400)
                    .entity(errorBody(400, message))
                    .build();
        }
    }

    private static Map<String, Object> errorBody(int status, String message) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status", status,
                "message", message);
    }
}
