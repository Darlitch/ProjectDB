package com.db.project.core.service;

import com.db.project.core.model.ProductionProcess;
import com.db.project.api.dto.product.ProductShortDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessCreateDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessUpdateDTO;

import java.util.List;

public interface ProductionProcessService {

    ProductionProcessDTO create(ProductionProcessCreateDTO productionProcessDTO);

    List<ProductionProcessDTO> getAll();

    ProductionProcessDTO getById(Integer id);

    ProductionProcess getEntityById(Integer id);

    ProductionProcessDTO update(Integer id, ProductionProcessUpdateDTO productionProcessDTO);

    void delete(Integer id);

    List<ProductionProcessDTO> getByProduct(Integer productId);

    List<ProductShortDTO> getCurrentProductsInProduction(Integer workshopId, Integer sectionId, Integer categoryId);
} 