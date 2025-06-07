package com.db.project.api.mapper.productattribute;

import com.db.project.core.model.ProductAttribute;
import com.db.project.api.dto.productattribute.ProductAttributeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductAttributeUpdateMapper {
    void updateFromDto(ProductAttributeUpdateDTO dto, @MappingTarget ProductAttribute entity);
} 