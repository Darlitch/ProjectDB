package com.db.project.api.mapper.test;

import com.db.project.core.model.Test;
import com.db.project.core.service.ProductService;
import com.db.project.core.service.TestLabService;
import com.db.project.api.dto.test.TestUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProductService.class, TestLabService.class})
public interface TestUpdateMapper {
    @Mapping(target = "product", source = "productId", qualifiedByName = "getEntityById")
    @Mapping(target = "lab", source = "labId", qualifiedByName = "getEntityById")
    void updateFromDto(TestUpdateDTO dto, @MappingTarget Test entity);
} 