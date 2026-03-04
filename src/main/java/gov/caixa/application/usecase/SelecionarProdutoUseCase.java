package gov.caixa.application.usecase;

import gov.caixa.domain.exception.ProdutoNaoElegivelException;
import gov.caixa.domain.model.Produto;
import gov.caixa.domain.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@ApplicationScoped
public class SelecionarProdutoUseCase {

    private final ProdutoRepository produtoRepository;

    public SelecionarProdutoUseCase(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(String tipoProduto, BigDecimal valor, int prazoMeses) {
        return produtoRepository.buscarElegivel(tipoProduto, valor, prazoMeses)
                .orElseThrow(() -> new ProdutoNaoElegivelException(tipoProduto, valor.doubleValue(), prazoMeses));
    }
}
