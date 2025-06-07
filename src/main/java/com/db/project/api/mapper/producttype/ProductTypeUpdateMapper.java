package com.db.project.api.mapper.producttype;

import com.db.project.core.model.ProductType;
import com.db.project.api.dto.producttype.ProductTypeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductTypeUpdateMapper {
    void updateFromDto(ProductTypeUpdateDTO dto, @MappingTarget ProductType entity);
} 