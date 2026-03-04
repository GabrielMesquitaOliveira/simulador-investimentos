package gov.caixa.presentation.dto;

import gov.caixa.domain.model.Produto;
import gov.caixa.domain.model.Simulacao;

import java.math.BigDecimal;
import java.time.Instant;

public record SimulacaoResponse(
                ProdutoValidado produtoValidado,
                ResultadoSimulacao resultadoSimulacao,
                Instant dataSimulacao) {

        public record ProdutoValidado(
                        Long id,
                        String nome,
                        String tipo,
                        BigDecimal rentabilidade,
                        String risco) {
        }

        public record ResultadoSimulacao(
                        BigDecimal valorFinal,
                        int prazoMeses) {
        }

        public static SimulacaoResponse from(Simulacao simulacao, Produto produto) {
                return new SimulacaoResponse(
                                new ProdutoValidado(
                                                produto.id(),
                                                produto.nome(),
                                                produto.tipoProduto(),
                                                produto.rentabilidadeAnual(),
                                                produto.risco()),
                                new ResultadoSimulacao(
                                                simulacao.valorFinal(),
                                                simulacao.prazoMeses()),
                                simulacao.dataSimulacao());
        }
}
