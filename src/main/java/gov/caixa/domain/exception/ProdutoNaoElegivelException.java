package gov.caixa.domain.exception;

public class ProdutoNaoElegivelException extends RuntimeException {

    public ProdutoNaoElegivelException(String tipoProduto, double valor, int prazoMeses) {
        super(String.format(
                "Nenhum produto elegível encontrado para tipo=%s, valor=%.2f, prazo=%d meses.",
                tipoProduto, valor, prazoMeses));
    }
}
