package gov.caixa.domain.repository;

import gov.caixa.domain.model.Produto;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProdutoRepository {

    Optional<Produto> buscarElegivel(String tipoProduto, BigDecimal valor, int prazoMeses);
}
