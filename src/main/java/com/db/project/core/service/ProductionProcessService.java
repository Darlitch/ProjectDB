package com.db.project.core.service;

import com.db.project.core.model.ProductionProcess;
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
} 