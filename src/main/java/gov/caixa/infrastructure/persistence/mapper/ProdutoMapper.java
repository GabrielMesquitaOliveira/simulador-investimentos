package gov.caixa.infrastructure.persistence.mapper;

import gov.caixa.domain.model.Produto;
import gov.caixa.infrastructure.persistence.entity.ProdutoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProdutoMapper {

    Produto toDomain(ProdutoEntity entity);
}
