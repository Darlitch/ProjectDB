package com.db.project.core.service;

import com.db.project.core.model.ProductCategory;
import com.db.project.api.dto.productcategory.ProductCategoryCreateDTO;
import com.db.project.api.dto.productcategory.ProductCategoryDTO;
import com.db.project.api.dto.productcategory.ProductCategoryUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryDTO create(ProductCategoryCreateDTO categoryDTO);

    List<ProductCategoryDTO> getAll();

    ProductCategoryDTO getById(Integer id);

    @Named("getEntityById")
    ProductCategory getEntityById(Integer id);

    ProductCategoryDTO update(Integer id, ProductCategoryUpdateDTO categoryDTO);

    void delete(Integer id);
} 