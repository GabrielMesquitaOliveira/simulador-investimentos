package gov.caixa.infrastructure.persistence.mapper;

import gov.caixa.domain.model.Simulacao;
import gov.caixa.infrastructure.persistence.entity.SimulacaoEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class SimulacaoMapperTest {

    @Inject
    SimulacaoMapper mapper;

    @Test
    void deveMapearSimulacaoEntityParaDomain() {
        SimulacaoEntity entity = new SimulacaoEntity();
        entity.setId(1L);
        entity.setClienteId(2L);
        entity.setProdutoNome("LCI");
        entity.setTipoProduto("LCI");
        entity.setValorInvestido(new BigDecimal("1000"));
        entity.setPrazoMeses(12);
        entity.setRentabilidadeAplicada(new BigDecimal("0.1"));
        entity.setValorFinal(new BigDecimal("1100"));
        entity.setDataSimulacao(Instant.now());

        Simulacao domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(1L, domain.id());
        assertEquals(2L, domain.clienteId());
        assertEquals("LCI", domain.produtoNome());
    }

    @Test
    void deveRetornarNullQuandoEntityForNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void deveMapearSimulacaoDomainParaEntity() {
        Simulacao domain = new Simulacao(1L, 2L, "LCI", "LCI", new BigDecimal("1000"), 12, new BigDecimal("0.1"),
                new BigDecimal("1100"), Instant.now());

        SimulacaoEntity entity = mapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(2L, entity.getClienteId());
        assertEquals("LCI", entity.getProdutoNome());
    }

    @Test
    void deveRetornarNullQuandoDomainForNull() {
        assertNull(mapper.toEntity(null));
    }
}
