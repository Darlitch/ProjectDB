package com.db.project.api.mapper.productionprocess;

import com.db.project.core.model.ProductionProcess;
import com.db.project.api.dto.product.ProductShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductionProcessStatisticsMapper {
    
    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "serialNum", source = "product.serialNum")
    @Mapping(target = "type", source = "product.type")
    @Mapping(target = "workshop", source = "job.section.workshop")
    ProductShortDTO toProductShortDto(ProductionProcess process);
    
    List<ProductShortDTO> toProductShortDto(List<ProductionProcess> processes);
} 