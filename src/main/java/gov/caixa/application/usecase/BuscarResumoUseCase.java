package gov.caixa.application.usecase;

import gov.caixa.domain.repository.SimulacaoRepository;
import gov.caixa.domain.valueobject.ResumoSimulacoes;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BuscarResumoUseCase {

    private final SimulacaoRepository simulacaoRepository;

    public BuscarResumoUseCase(SimulacaoRepository simulacaoRepository) {
        this.simulacaoRepository = simulacaoRepository;
    }

    public ResumoSimulacoes executar(Long clienteId) {
        var simulacoes = simulacaoRepository.buscarPorCliente(clienteId);
        return ResumoSimulacoes.calcular(simulacoes);
    }
}
