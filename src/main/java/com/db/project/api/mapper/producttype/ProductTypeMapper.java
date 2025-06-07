package com.db.project.api.mapper.producttype;

import com.db.project.core.model.ProductType;
import com.db.project.api.dto.producttype.ProductTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeMapper {
    ProductTypeDTO toDto(ProductType entity);
    List<ProductTypeDTO> toDto(List<ProductType> entities);
} 