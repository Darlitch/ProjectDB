package com.db.project.api.mapper.product;

import com.db.project.core.model.Product;
import com.db.project.api.dto.product.ProductDTO;
import com.db.project.api.dto.product.ProductShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductDTO toDto(Product entity);
    List<ProductDTO> toDto(List<Product> entities);
    ProductShortDTO toShortDto(Product entity);
    List<ProductShortDTO> toShortDto(List<Product> entities);
} 