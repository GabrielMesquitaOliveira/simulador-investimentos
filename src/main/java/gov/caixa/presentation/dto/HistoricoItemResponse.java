package gov.caixa.presentation.dto;

import gov.caixa.domain.model.Simulacao;

import java.math.BigDecimal;
import java.time.Instant;

public record HistoricoItemResponse(
        Long id,
        Long clienteId,
        String produto,
        BigDecimal valorInvestido,
        BigDecimal valorFinal,
        int prazoMeses,
        Instant dataSimulacao) {

    public static HistoricoItemResponse from(Simulacao simulacao) {
        return new HistoricoItemResponse(
                simulacao.id(),
                simulacao.clienteId(),
                simulacao.produtoNome(),
                simulacao.valorInvestido(),
                simulacao.valorFinal(),
                simulacao.prazoMeses(),
                simulacao.dataSimulacao());
    }
}
