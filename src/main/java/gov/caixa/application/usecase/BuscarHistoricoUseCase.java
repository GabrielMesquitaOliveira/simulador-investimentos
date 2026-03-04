package gov.caixa.application.usecase;

import gov.caixa.domain.model.Simulacao;
import gov.caixa.domain.repository.SimulacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BuscarHistoricoUseCase {

    private final SimulacaoRepository simulacaoRepository;

    public BuscarHistoricoUseCase(SimulacaoRepository simulacaoRepository) {
        this.simulacaoRepository = simulacaoRepository;
    }

    public List<Simulacao> executar(Long clienteId) {
        return simulacaoRepository.buscarPorCliente(clienteId);
    }
}
