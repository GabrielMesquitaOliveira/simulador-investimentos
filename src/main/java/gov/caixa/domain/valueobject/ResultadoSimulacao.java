package gov.caixa.domain.valueobject;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Immutable value object carrying the result of a simulation calculation.
 * Encapsulates the compound interest formula:
 * valorFinal = valor × (1 + rentabilidadeAnual / 12) ^ prazoMeses
 */
public record ResultadoSimulacao(
        BigDecimal valorFinal,
        int prazoMeses,
        BigDecimal rentabilidadeAplicada) {

    public static ResultadoSimulacao calcular(BigDecimal valor, int prazoMeses, BigDecimal rentabilidadeAnual) {
        BigDecimal taxaMensal = rentabilidadeAnual.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal fator = BigDecimal.ONE.add(taxaMensal).pow(prazoMeses, MathContext.DECIMAL128);
        BigDecimal valorFinal = valor.multiply(fator).setScale(2, RoundingMode.HALF_UP);
        return new ResultadoSimulacao(valorFinal, prazoMeses, rentabilidadeAnual);
    }
}
