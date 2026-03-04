package gov.caixa.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoSimulacaoTest {

    @Test
    void deveCalcularValorFinalCorretamente() {
        // Arrange — R$ 10.000, 12% a.a., 12 meses
        // Esperado: 10000 * (1 + 0.01)^12 ≈ 11268.25
        BigDecimal valor = new BigDecimal("10000.00");
        BigDecimal rentabilidade = new BigDecimal("0.12");
        int prazo = 12;

        // Act
        ResultadoSimulacao resultado = ResultadoSimulacao.calcular(valor, prazo, rentabilidade);

        // Assert
        assertNotNull(resultado);
        assertEquals(prazo, resultado.prazoMeses());
        assertEquals(0, rentabilidade.compareTo(resultado.rentabilidadeAplicada()));
        // Tolerância de R$ 0,10 para arredondamentos
        assertTrue(resultado.valorFinal().compareTo(new BigDecimal("11268.00")) >= 0);
        assertTrue(resultado.valorFinal().compareTo(new BigDecimal("11269.00")) <= 0);
    }

    @Test
    void deveRetornarValorOriginalParaPrazoZero() {
        // Arrange
        BigDecimal valor = new BigDecimal("5000.00");
        BigDecimal rentabilidade = new BigDecimal("0.08");

        // Act
        ResultadoSimulacao resultado = ResultadoSimulacao.calcular(valor, 0, rentabilidade);

        // Assert
        assertEquals(0, new BigDecimal("5000.00").compareTo(resultado.valorFinal()));
    }

    @Test
    void deveValidarMetodosDeRecord() {
        ResultadoSimulacao r1 = new ResultadoSimulacao(new BigDecimal("100"), 12, new BigDecimal("0.1"));
        ResultadoSimulacao r2 = new ResultadoSimulacao(new BigDecimal("100"), 12, new BigDecimal("0.1"));
        ResultadoSimulacao r3 = new ResultadoSimulacao(new BigDecimal("200"), 12, new BigDecimal("0.1"));

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotNull(r1.toString());
    }
}
