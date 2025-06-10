package com.db.project.api.mapper.test;

import com.db.project.core.model.Test;
import com.db.project.api.dto.product.ProductShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestStatisticsMapper {
    
    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "serialNum", source = "product.serialNum")
    @Mapping(target = "type", source = "product.type")
    @Mapping(target = "workshop", source = "product.workshop")
    ProductShortDTO toProductShortDto(Test test);
    
    List<ProductShortDTO> toProductShortDto(List<Test> tests);
} 