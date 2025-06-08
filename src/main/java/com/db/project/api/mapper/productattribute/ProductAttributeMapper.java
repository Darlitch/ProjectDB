package com.db.project.api.mapper.productattribute;

import com.db.project.core.model.ProductAttribute;
import com.db.project.api.dto.productattribute.ProductAttributeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductAttributeMapper {
    @Mapping(target = "attributeValues", source = "values")
    ProductAttributeDTO toDto(ProductAttribute entity);
    List<ProductAttributeDTO> toDto(List<ProductAttribute> entities);
} 