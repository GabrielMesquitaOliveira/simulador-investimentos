package gov.caixa.domain.service;

import gov.caixa.domain.exception.ProdutoNaoElegivelException;
import gov.caixa.domain.model.Produto;
import gov.caixa.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoSelectorServiceTest {

    private ProdutoRepository produtoRepository;
    private ProdutoSelectorService sut;

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoRepository.class);
        sut = new ProdutoSelectorService(produtoRepository);
    }

    @Test
    void deveRetornarProdutoQuandoEncontrado() {
        // Arrange
        Produto produto = new Produto(1L, "CDB Caixa 2026", "CDB",
                new BigDecimal("0.12"), "Baixo", 6, 24,
                new BigDecimal("1000.00"), new BigDecimal("500000.00"));

        when(produtoRepository.buscarElegivel("CDB", new BigDecimal("10000.00"), 12))
                .thenReturn(Optional.of(produto));

        // Act
        Produto resultado = sut.selecionar("CDB", new BigDecimal("10000.00"), 12);

        // Assert
        assertNotNull(resultado);
        assertEquals("CDB Caixa 2026", resultado.nome());
        verify(produtoRepository).buscarElegivel("CDB", new BigDecimal("10000.00"), 12);
    }

    @Test
    void deveLancarExcecaoQuandoNenhumProdutoElegivel() {
        // Arrange
        when(produtoRepository.buscarElegivel("LCI", new BigDecimal("500.00"), 3))
                .thenReturn(Optional.empty());

        // Act & Assert
        ProdutoNaoElegivelException ex = assertThrows(
                ProdutoNaoElegivelException.class,
                () -> sut.selecionar("LCI", new BigDecimal("500.00"), 3));

        assertTrue(ex.getMessage().contains("LCI"));
        assertTrue(ex.getMessage().contains("3 meses"));
    }
}
