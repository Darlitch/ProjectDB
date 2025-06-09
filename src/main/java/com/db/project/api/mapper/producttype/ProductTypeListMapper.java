package com.db.project.api.mapper.producttype;

import com.db.project.core.model.ProductType;
import com.db.project.api.dto.producttype.ProductTypeListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeListMapper {
    ProductTypeListDTO toDto(ProductType entity);
    List<ProductTypeListDTO> toDto(List<ProductType> entities);
} 