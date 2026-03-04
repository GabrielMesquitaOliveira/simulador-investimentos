package gov.caixa.infrastructure.persistence.mapper;

import gov.caixa.domain.model.Simulacao;
import gov.caixa.infrastructure.persistence.entity.SimulacaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface SimulacaoMapper {

    Simulacao toDomain(SimulacaoEntity entity);

    SimulacaoEntity toEntity(Simulacao domain);
}
