package gov.caixa.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProdutoNaoElegivelExceptionTest {

    @Test
    void deveCriarExceptionComMensagemCorreta() {
        String tipo = "CDB";
        double valor = 1000.0;
        int prazo = 12;
        ProdutoNaoElegivelException exception = new ProdutoNaoElegivelException(tipo, valor, prazo);

        assertNotNull(exception);
        assertEquals("Nenhum produto elegível encontrado para tipo=CDB, valor=1000.00, prazo=12 meses.",
                exception.getMessage());
    }
}
