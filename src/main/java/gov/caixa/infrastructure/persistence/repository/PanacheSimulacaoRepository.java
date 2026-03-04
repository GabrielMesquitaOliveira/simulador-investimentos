package gov.caixa.infrastructure.persistence.repository;

import gov.caixa.domain.model.Simulacao;
import gov.caixa.domain.repository.SimulacaoRepository;
import gov.caixa.infrastructure.persistence.entity.SimulacaoEntity;
import gov.caixa.infrastructure.persistence.mapper.SimulacaoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PanacheSimulacaoRepository implements SimulacaoRepository, PanacheRepository<SimulacaoEntity> {

    private final SimulacaoMapper simulacaoMapper;

    public PanacheSimulacaoRepository(SimulacaoMapper simulacaoMapper) {
        this.simulacaoMapper = simulacaoMapper;
    }

    @Override
    public Simulacao salvar(Simulacao simulacao) {
        SimulacaoEntity entity = simulacaoMapper.toEntity(simulacao);
        persist(entity);
        return simulacaoMapper.toDomain(entity);
    }

    @Override
    public List<Simulacao> buscarPorCliente(Long clienteId) {
        return list("clienteId", clienteId)
                .stream()
                .map(simulacaoMapper::toDomain)
                .toList();
    }
}
