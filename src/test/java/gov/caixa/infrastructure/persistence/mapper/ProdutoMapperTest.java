package gov.caixa.infrastructure.persistence.mapper;

import gov.caixa.domain.model.Produto;
import gov.caixa.infrastructure.persistence.entity.ProdutoEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class ProdutoMapperTest {

    @Inject
    ProdutoMapper mapper;

    @Test
    void deveMapearProdutoEntityParaDomain() {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(1L);
        entity.setNome("CDB");
        entity.setTipoProduto("CDB");
        entity.setRentabilidadeAnual(new BigDecimal("0.1"));
        entity.setRisco("Baixo");
        entity.setPrazoMinMeses(1);
        entity.setPrazoMaxMeses(12);
        entity.setValorMin(new BigDecimal("100"));
        entity.setValorMax(new BigDecimal("1000"));

        Produto domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(1L, domain.id());
        assertEquals("CDB", domain.nome());
    }

    @Test
    void deveRetornarNullQuandoEntityForNull() {
        assertNull(mapper.toDomain(null));
    }
}
