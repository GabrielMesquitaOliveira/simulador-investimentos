package gov.caixa.application.usecase;

import gov.caixa.domain.model.Simulacao;
import gov.caixa.domain.repository.SimulacaoRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class BuscarHistoricoUseCaseTest {

    @Test
    void deveBuscarHistoricoDeSimulacoesDoCliente() {
        // Arrange
        SimulacaoRepository repository = mock(SimulacaoRepository.class);
        BuscarHistoricoUseCase useCase = new BuscarHistoricoUseCase(repository);

        List<Simulacao> simulacoes = List.of(
                new Simulacao(1L, 123L, "LCI", "LCI", new BigDecimal("1000"), 12, new BigDecimal("0.1"),
                        new BigDecimal("1100"), Instant.now()));

        when(repository.buscarPorCliente(123L)).thenReturn(simulacoes);

        // Act
        List<Simulacao> resultado = useCase.executar(123L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("LCI", resultado.get(0).produtoNome());
        verify(repository).buscarPorCliente(123L);
    }
}
