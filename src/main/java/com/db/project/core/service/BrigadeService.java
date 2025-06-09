package com.db.project.core.service;

import com.db.project.core.model.Brigade;
import com.db.project.api.dto.brigade.BrigadeCreateDTO;
import com.db.project.api.dto.brigade.BrigadeDTO;
import com.db.project.api.dto.brigade.BrigadeUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface BrigadeService {

    BrigadeDTO create(BrigadeCreateDTO brigadeDTO);

    List<BrigadeDTO> getAll();

    BrigadeDTO getById(Integer id);

    @Named("getEntityById")
    Brigade getEntityById(Integer id);

    BrigadeDTO update(Integer id, BrigadeUpdateDTO brigadeDTO);

    void delete(Integer id);

    List<BrigadeDTO> getByWorkshopOrSection(Integer workshopId, Integer sectionId);

    List<BrigadeDTO> getByProduct(Integer productId);
} 