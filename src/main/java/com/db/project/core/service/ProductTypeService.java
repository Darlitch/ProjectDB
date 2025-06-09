package com.db.project.core.service;

import com.db.project.core.model.ProductType;
import com.db.project.api.dto.producttype.ProductTypeCreateDTO;
import com.db.project.api.dto.producttype.ProductTypeDTO;
import com.db.project.api.dto.producttype.ProductTypeListDTO;
import com.db.project.api.dto.producttype.ProductTypeUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface ProductTypeService {

    ProductTypeDTO create(ProductTypeCreateDTO productTypeDTO);

    List<ProductTypeDTO> getAll();

    ProductTypeDTO getById(Integer id);

    @Named("getEntityById")
    ProductType getEntityById(Integer id);

    ProductTypeDTO update(Integer id, ProductTypeUpdateDTO productTypeDTO);

    void delete(Integer id);

    // Методы для получения перечня видов изделий
    List<ProductTypeListDTO> getTypesByCategory(Integer categoryId);
    
    List<ProductTypeListDTO> getTypesByWorkshop(Integer workshopId);

    List<ProductTypeListDTO> getTypesByWorkshopAndCategory(Integer workshopId, Integer categoryId);
} 