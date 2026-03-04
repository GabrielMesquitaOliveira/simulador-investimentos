package gov.caixa.infrastructure.persistence.repository;

import gov.caixa.domain.model.Produto;
import gov.caixa.domain.repository.ProdutoRepository;
import gov.caixa.infrastructure.persistence.entity.ProdutoEntity;
import gov.caixa.infrastructure.persistence.mapper.ProdutoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.Optional;

@ApplicationScoped
public class PanacheProdutoRepository implements ProdutoRepository, PanacheRepository<ProdutoEntity> {

    private final ProdutoMapper produtoMapper;

    public PanacheProdutoRepository(ProdutoMapper produtoMapper) {
        this.produtoMapper = produtoMapper;
    }

    @Override
    public Optional<Produto> buscarElegivel(String tipoProduto, BigDecimal valor, int prazoMeses) {
        return find(
                "tipoProduto = ?1 AND valorMin <= ?2 AND valorMax >= ?2 AND prazoMinMeses <= ?3 AND prazoMaxMeses >= ?3",
                tipoProduto, valor, prazoMeses)
                .firstResultOptional()
                .map(produtoMapper::toDomain);
    }
}
