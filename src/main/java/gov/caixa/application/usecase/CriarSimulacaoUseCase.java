package gov.caixa.application.usecase;

import gov.caixa.domain.model.Simulacao;
import gov.caixa.domain.repository.SimulacaoRepository;
import gov.caixa.domain.valueobject.ResultadoSimulacao;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.time.Instant;

@ApplicationScoped
public class CriarSimulacaoUseCase {

    private final SelecionarProdutoUseCase selecionarProdutoUseCase;
    private final SimulacaoRepository simulacaoRepository;

    public CriarSimulacaoUseCase(SelecionarProdutoUseCase selecionarProdutoUseCase,
            SimulacaoRepository simulacaoRepository) {
        this.selecionarProdutoUseCase = selecionarProdutoUseCase;
        this.simulacaoRepository = simulacaoRepository;
    }

    public Simulacao executar(Long clienteId, BigDecimal valor, int prazoMeses, String tipoProduto) {
        var produto = selecionarProdutoUseCase.executar(tipoProduto, valor, prazoMeses);
        var resultado = ResultadoSimulacao.calcular(valor, prazoMeses, produto.rentabilidadeAnual());

        var simulacao = new Simulacao(
                null,
                clienteId,
                produto.nome(),
                produto.tipoProduto(),
                valor,
                prazoMeses,
                resultado.rentabilidadeAplicada(),
                resultado.valorFinal(),
                Instant.now());

        return simulacaoRepository.salvar(simulacao);
    }
}
