package com.db.project.api.mapper.productionprocess;

import com.db.project.core.model.ProductionProcess;
import com.db.project.api.dto.productionprocess.ProductionProcessDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductionProcessMapper {
    ProductionProcessDTO toDto(ProductionProcess entity);
    List<ProductionProcessDTO> toDto(List<ProductionProcess> entities);
} 