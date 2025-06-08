package com.db.project.api.mapper.producttype;

import com.db.project.core.model.ProductType;
import com.db.project.core.service.ProductCategoryService;
import com.db.project.api.dto.producttype.ProductTypeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProductCategoryService.class})
public interface ProductTypeUpdateMapper {
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "getEntityById")
    void updateFromDto(ProductTypeUpdateDTO dto, @MappingTarget ProductType entity);
} 