package com.db.project.api.mapper.product;

import com.db.project.core.model.Product;
import com.db.project.api.dto.product.ProductUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductUpdateMapper {
    void updateFromDto(ProductUpdateDTO dto, @MappingTarget Product entity);
} 