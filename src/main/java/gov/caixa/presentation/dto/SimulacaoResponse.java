package gov.caixa.presentation.dto;

import gov.caixa.domain.model.Simulacao;

import java.math.BigDecimal;
import java.time.Instant;

public record SimulacaoResponse(
        ProdutoValidado produtoValidado,
        ResultadoSimulacao resultadoSimulacao,
        Instant dataSimulacao) {

    public record ProdutoValidado(
            String nome,
            String tipo,
            BigDecimal rentabilidade,
            String risco) {
    }

    public record ResultadoSimulacao(
            BigDecimal valorFinal,
            int prazoMeses) {
    }

    public static SimulacaoResponse from(Simulacao simulacao) {
        return new SimulacaoResponse(
                new ProdutoValidado(
                        simulacao.produtoNome(),
                        simulacao.tipoProduto(),
                        simulacao.rentabilidadeAplicada(),
                        null),
                new ResultadoSimulacao(
                        simulacao.valorFinal(),
                        simulacao.prazoMeses()),
                simulacao.dataSimulacao());
    }
}
