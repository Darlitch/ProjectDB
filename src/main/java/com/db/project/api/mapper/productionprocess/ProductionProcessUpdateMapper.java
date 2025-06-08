package com.db.project.api.mapper.productionprocess;

import com.db.project.core.model.ProductionProcess;
import com.db.project.core.service.ProductService;
import com.db.project.core.service.BrigadeService;
import com.db.project.core.service.AssemblyJobService;
import com.db.project.api.dto.productionprocess.ProductionProcessUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProductService.class, BrigadeService.class, AssemblyJobService.class})
public interface ProductionProcessUpdateMapper {
    @Mapping(target = "product", source = "productId", qualifiedByName = "getEntityById")
    @Mapping(target = "brigade", source = "brigadeId", qualifiedByName = "getEntityById")
    @Mapping(target = "job", source = "jobId", qualifiedByName = "getEntityById")
    void updateFromDto(ProductionProcessUpdateDTO dto, @MappingTarget ProductionProcess entity);
} 