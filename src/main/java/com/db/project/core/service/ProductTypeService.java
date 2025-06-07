package com.db.project.core.service;

import com.db.project.core.model.ProductType;
import com.db.project.api.dto.producttype.ProductTypeCreateDTO;
import com.db.project.api.dto.producttype.ProductTypeDTO;
import com.db.project.api.dto.producttype.ProductTypeUpdateDTO;

import java.util.List;

public interface ProductTypeService {

    ProductTypeDTO create(ProductTypeCreateDTO productTypeDTO);

    List<ProductTypeDTO> getAll();

    ProductTypeDTO getById(Integer id);

    ProductType getEntityById(Integer id);

    ProductTypeDTO update(Integer id, ProductTypeUpdateDTO productTypeDTO);

    void delete(Integer id);
} 