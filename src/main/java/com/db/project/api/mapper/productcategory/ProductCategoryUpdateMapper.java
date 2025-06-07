package com.db.project.api.mapper.productcategory;

import com.db.project.core.model.ProductCategory;
import com.db.project.api.dto.productcategory.ProductCategoryUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryUpdateMapper {
    void updateFromDto(ProductCategoryUpdateDTO dto, @MappingTarget ProductCategory entity);
}

