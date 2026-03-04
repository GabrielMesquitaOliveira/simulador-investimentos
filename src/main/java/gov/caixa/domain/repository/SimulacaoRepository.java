package gov.caixa.domain.repository;

import gov.caixa.domain.model.Simulacao;

import java.util.List;

public interface SimulacaoRepository {

    Simulacao salvar(Simulacao simulacao);

    List<Simulacao> buscarPorCliente(Long clienteId);
}
