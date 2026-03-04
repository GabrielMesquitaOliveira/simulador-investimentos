package gov.caixa.domain.valueobject;

import gov.caixa.domain.model.Simulacao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResumoSimulacoesTest {

    @Test
    void deveCalcularResumoCorretamente() {
        var s1 = new Simulacao(1L, 1L, "CDB", "CDB",
                new BigDecimal("10000.00"), 12, new BigDecimal("0.12"),
                new BigDecimal("11268.25"), Instant.now());
        var s2 = new Simulacao(2L, 1L, "LCI", "LCI",
                new BigDecimal("5000.00"), 24, new BigDecimal("0.10"),
                new BigDecimal("6100.00"), Instant.now());

        var resumo = ResumoSimulacoes.calcular(List.of(s1, s2));

        assertEquals(2, resumo.totalSimulacoes());
        assertEquals(0, new BigDecimal("15000.00").compareTo(resumo.totalInvestido()));
        assertEquals(0, new BigDecimal("17368.25").compareTo(resumo.totalProjetado()));
        assertEquals(0, new BigDecimal("2368.25").compareTo(resumo.ganhoProjetado()));
        // média: (0.12 + 0.10) / 2 = 0.11
        assertEquals(0, new BigDecimal("0.1100").compareTo(resumo.rentabilidadeMediaAnual()));
    }

    @Test
    void deveRetornarZerosParaListaVazia() {
        var resumo = ResumoSimulacoes.calcular(List.of());

        assertEquals(0, resumo.totalSimulacoes());
        assertEquals(0, BigDecimal.ZERO.compareTo(resumo.totalInvestido()));
        assertEquals(0, BigDecimal.ZERO.compareTo(resumo.rentabilidadeMediaAnual()));
    }
}
