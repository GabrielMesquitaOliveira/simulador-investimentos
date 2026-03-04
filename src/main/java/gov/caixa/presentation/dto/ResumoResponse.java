package gov.caixa.presentation.dto;

import gov.caixa.domain.valueobject.ResumoSimulacoes;

import java.math.BigDecimal;

public record ResumoResponse(
        long totalSimulacoes,
        BigDecimal totalInvestido,
        BigDecimal totalProjetado,
        BigDecimal ganhoProjetado,
        BigDecimal rentabilidadeMediaAnual) {

    public static ResumoResponse from(ResumoSimulacoes resumo) {
        return new ResumoResponse(
                resumo.totalSimulacoes(),
                resumo.totalInvestido(),
                resumo.totalProjetado(),
                resumo.ganhoProjetado(),
                resumo.rentabilidadeMediaAnual());
    }
}
