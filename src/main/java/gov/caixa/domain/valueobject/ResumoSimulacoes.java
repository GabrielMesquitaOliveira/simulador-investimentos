package gov.caixa.domain.valueobject;

import gov.caixa.domain.model.Simulacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Immutable value object aggregating financial metrics across a client's
 * simulations.
 */
public record ResumoSimulacoes(
        long totalSimulacoes,
        BigDecimal totalInvestido,
        BigDecimal totalProjetado,
        BigDecimal ganhoProjetado,
        BigDecimal rentabilidadeMediaAnual) {

    public static ResumoSimulacoes calcular(List<Simulacao> simulacoes) {
        long total = simulacoes.size();

        BigDecimal totalInvestido = simulacoes.stream()
                .map(Simulacao::valorInvestido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalProjetado = simulacoes.stream()
                .map(Simulacao::valorFinal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal ganhoProjetado = totalProjetado.subtract(totalInvestido);

        BigDecimal rentabilidadeMedia = simulacoes.isEmpty()
                ? BigDecimal.ZERO
                : simulacoes.stream()
                        .map(Simulacao::rentabilidadeAplicada)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP);

        return new ResumoSimulacoes(total, totalInvestido, totalProjetado, ganhoProjetado, rentabilidadeMedia);
    }
}
