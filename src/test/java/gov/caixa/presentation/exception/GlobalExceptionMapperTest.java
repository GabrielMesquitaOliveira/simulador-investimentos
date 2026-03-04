package gov.caixa.presentation.exception;

import gov.caixa.domain.exception.ProdutoNaoElegivelException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GlobalExceptionMapperTest {

    private final GlobalExceptionMapper.ProdutoNaoElegivelMapper produtoMapper = new GlobalExceptionMapper.ProdutoNaoElegivelMapper();

    private final GlobalExceptionMapper.ConstraintViolationMapper constraintMapper = new GlobalExceptionMapper.ConstraintViolationMapper();

    @Test
    void deveMapearProdutoNaoElegivelParaStatus422() {
        ProdutoNaoElegivelException ex = new ProdutoNaoElegivelException("CDB", 100.0, 10);
        Response response = produtoMapper.toResponse(ex);

        assertEquals(422, response.getStatus());
        Map<String, Object> entity = (Map<String, Object>) response.getEntity();
        assertNotNull(entity.get("timestamp"));
        assertEquals(422, entity.get("status"));
        assertEquals("Nenhum produto elegível encontrado para tipo=CDB, valor=100.00, prazo=10 meses.",
                entity.get("message"));
    }

    @Test
    void deveMapearConstraintViolationParaStatus400() {
        ConstraintViolation<?> violation = Mockito.mock(ConstraintViolation.class);
        Path path = Mockito.mock(Path.class);

        when(violation.getPropertyPath()).thenReturn(path);
        when(path.toString()).thenReturn("valor");
        when(violation.getMessage()).thenReturn("não pode ser nulo");

        ConstraintViolationException ex = new ConstraintViolationException(Set.of(violation));

        Response response = constraintMapper.toResponse(ex);

        assertEquals(400, response.getStatus());
        Map<String, Object> entity = (Map<String, Object>) response.getEntity();
        assertNotNull(entity.get("timestamp"));
        assertEquals(400, entity.get("status"));
        assertEquals("valor: não pode ser nulo", entity.get("message"));
    }
}
