package com.db.project.core.service;

import com.db.project.core.model.Product;
import com.db.project.api.dto.product.ProductCreateDTO;
import com.db.project.api.dto.product.ProductDTO;
import com.db.project.api.dto.product.ProductUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface ProductService {

    ProductDTO create(ProductCreateDTO productDTO);

    List<ProductDTO> getAll();

    ProductDTO getById(Integer id);

    @Named("getEntityById")
    Product getEntityById(Integer id);

    ProductDTO update(Integer id, ProductUpdateDTO productDTO);

    void delete(Integer id);
} 