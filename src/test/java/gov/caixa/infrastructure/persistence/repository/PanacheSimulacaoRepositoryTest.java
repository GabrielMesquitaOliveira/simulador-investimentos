package gov.caixa.infrastructure.persistence.repository;

import gov.caixa.domain.model.Simulacao;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class PanacheSimulacaoRepositoryTest {

    @Inject
    PanacheSimulacaoRepository repository;

    @BeforeEach
    @Transactional
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    void deveSalvarEBuscarSimulacaoPorCliente() {
        Simulacao nova = new Simulacao(
                null,
                999L,
                "LCI Caixa",
                "LCI",
                new BigDecimal("5000.00"),
                12,
                new BigDecimal("0.08"),
                new BigDecimal("5400.00"),
                Instant.now());

        Simulacao salva = repository.salvar(nova);

        assertNotNull(salva.id());

        List<Simulacao> simulacoes = repository.buscarPorCliente(999L);
        assertEquals(1, simulacoes.size());
        assertEquals(salva.id(), simulacoes.get(0).id());
        assertEquals("LCI Caixa", simulacoes.get(0).produtoNome());
    }
}
