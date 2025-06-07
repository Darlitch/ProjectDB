package com.db.project.api.mapper.productionprocess;

import com.db.project.core.model.ProductionProcess;
import com.db.project.api.dto.productionprocess.ProductionProcessUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductionProcessUpdateMapper {
    void updateFromDto(ProductionProcessUpdateDTO dto, @MappingTarget ProductionProcess entity);
} 