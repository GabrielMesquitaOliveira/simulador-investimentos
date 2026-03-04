package gov.caixa.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

public record Simulacao(
        Long id,
        Long clienteId,
        String produtoNome,
        String tipoProduto,
        BigDecimal valorInvestido,
        int prazoMeses,
        BigDecimal rentabilidadeAplicada,
        BigDecimal valorFinal,
        Instant dataSimulacao) {
}
