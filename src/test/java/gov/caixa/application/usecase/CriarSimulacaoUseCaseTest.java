package gov.caixa.application.usecase;

import gov.caixa.domain.model.Produto;
import gov.caixa.domain.model.Simulacao;
import gov.caixa.domain.repository.SimulacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CriarSimulacaoUseCaseTest {

    private SelecionarProdutoUseCase selecionarProdutoUseCase;
    private SimulacaoRepository simulacaoRepository;
    private CriarSimulacaoUseCase sut;

    @BeforeEach
    void setUp() {
        selecionarProdutoUseCase = mock(SelecionarProdutoUseCase.class);
        simulacaoRepository = mock(SimulacaoRepository.class);
        sut = new CriarSimulacaoUseCase(selecionarProdutoUseCase, simulacaoRepository);
    }

    @Test
    void deveOrquestrarSimulacaoCompletoERetornarSimulacaoPersistida() {
        // Arrange
        Produto produto = new Produto(1L, "CDB Caixa 2026", "CDB",
                new BigDecimal("0.12"), "Baixo", 6, 24,
                new BigDecimal("1000.00"), new BigDecimal("500000.00"));

        Simulacao simulacaoSalva = new Simulacao(42L, 123L, "CDB Caixa 2026", "CDB",
                new BigDecimal("10000.00"), 12,
                new BigDecimal("0.12"), new BigDecimal("11268.25"),
                Instant.now());

        when(selecionarProdutoUseCase.executar("CDB", new BigDecimal("10000.00"), 12))
                .thenReturn(produto);
        when(simulacaoRepository.salvar(any(Simulacao.class)))
                .thenReturn(simulacaoSalva);

        // Act
        Simulacao resultado = sut.executar(123L, new BigDecimal("10000.00"), 12, "CDB");

        // Assert
        assertNotNull(resultado);
        assertEquals(42L, resultado.id());
        assertEquals(123L, resultado.clienteId());
        assertEquals("CDB Caixa 2026", resultado.produtoNome());
        assertTrue(resultado.valorFinal().compareTo(BigDecimal.ZERO) > 0);

        verify(selecionarProdutoUseCase).executar("CDB", new BigDecimal("10000.00"), 12);
        verify(simulacaoRepository).salvar(any(Simulacao.class));
    }
}
