package com.db.project.api.mapper.productcategory;

import com.db.project.core.model.ProductCategory;
import com.db.project.api.dto.productcategory.ProductCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductCategoryMapper {
    ProductCategoryDTO toDto(ProductCategory entity);
    List<ProductCategoryDTO> toDto(List<ProductCategory> entities);
} 