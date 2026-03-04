package gov.caixa.infrastructure.persistence.repository;

import gov.caixa.domain.model.Produto;
import gov.caixa.infrastructure.persistence.entity.ProdutoEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class PanacheProdutoRepositoryTest {

    @Inject
    PanacheProdutoRepository repository;

    @BeforeEach
    @Transactional
    void setUp() {
        repository.deleteAll();

        ProdutoEntity p1 = new ProdutoEntity();
        p1.setNome("LCI Caixa");
        p1.setTipoProduto("LCI");
        p1.setRentabilidadeAnual(new BigDecimal("0.08"));
        p1.setRisco("Baixo");
        p1.setPrazoMinMeses(6);
        p1.setPrazoMaxMeses(24);
        p1.setValorMin(new BigDecimal("1000.00"));
        p1.setValorMax(new BigDecimal("50000.00"));

        repository.persist(p1);
    }

    @Test
    void deveBuscarProdutoElegivel() {
        Optional<Produto> produto = repository.buscarElegivel("LCI", new BigDecimal("5000.00"), 12);

        assertTrue(produto.isPresent());
        assertEquals("LCI Caixa", produto.get().nome());
    }

    @Test
    void naoDeveBuscarProdutoElegivelSeValorForInvalido() {
        Optional<Produto> produtoBaixo = repository.buscarElegivel("LCI", new BigDecimal("500.00"), 12);
        assertTrue(produtoBaixo.isEmpty());

        Optional<Produto> produtoAlto = repository.buscarElegivel("LCI", new BigDecimal("100000.00"), 12);
        assertTrue(produtoAlto.isEmpty());
    }

    @Test
    void naoDeveBuscarProdutoElegivelSePrazoForInvalido() {
        Optional<Produto> produtoCurto = repository.buscarElegivel("LCI", new BigDecimal("5000.00"), 3);
        assertTrue(produtoCurto.isEmpty());

        Optional<Produto> produtoLongo = repository.buscarElegivel("LCI", new BigDecimal("5000.00"), 36);
        assertTrue(produtoLongo.isEmpty());
    }
}
