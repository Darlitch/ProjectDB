package com.db.project.core.service;

import com.db.project.core.model.ProductAttribute;
import com.db.project.api.dto.productattribute.ProductAttributeCreateDTO;
import com.db.project.api.dto.productattribute.ProductAttributeDTO;
import com.db.project.api.dto.productattribute.ProductAttributeUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface ProductAttributeService {

    ProductAttributeDTO create(ProductAttributeCreateDTO productAttributeDTO);

    List<ProductAttributeDTO> getAll();

    ProductAttributeDTO getById(Integer id);

    @Named("getEntityById")
    ProductAttribute getEntityById(Integer id);

    ProductAttributeDTO update(Integer id, ProductAttributeUpdateDTO productAttributeDTO);

    void delete(Integer id);
} 