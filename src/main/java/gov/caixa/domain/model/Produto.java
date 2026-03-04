package gov.caixa.domain.model;

import java.math.BigDecimal;

public record Produto(
        Long id,
        String nome,
        String tipoProduto,
        BigDecimal rentabilidadeAnual,
        String risco,
        int prazoMinMeses,
        int prazoMaxMeses,
        BigDecimal valorMin,
        BigDecimal valorMax) {
}
