package gov.caixa.domain.service;

import gov.caixa.domain.model.Produto;
import gov.caixa.domain.model.Simulacao;
import gov.caixa.domain.repository.SimulacaoRepository;
import gov.caixa.domain.valueobject.ResultadoSimulacao;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class SimulacaoService {

    private final ProdutoSelectorService produtoSelectorService;
    private final SimulacaoRepository simulacaoRepository;

    public SimulacaoService(ProdutoSelectorService produtoSelectorService,
            SimulacaoRepository simulacaoRepository) {
        this.produtoSelectorService = produtoSelectorService;
        this.simulacaoRepository = simulacaoRepository;
    }

    public Simulacao simular(Long clienteId, BigDecimal valor, int prazoMeses, String tipoProduto) {
        Produto produto = produtoSelectorService.selecionar(tipoProduto, valor, prazoMeses);

        ResultadoSimulacao resultado = ResultadoSimulacao.calcular(
                valor, prazoMeses, produto.rentabilidadeAnual());

        Simulacao simulacao = new Simulacao(
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

    public List<Simulacao> buscarHistorico(Long clienteId) {
        return simulacaoRepository.buscarPorCliente(clienteId);
    }
}
