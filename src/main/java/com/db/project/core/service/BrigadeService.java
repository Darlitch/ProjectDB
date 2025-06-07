package com.db.project.core.service;

import com.db.project.core.model.Brigade;
import com.db.project.api.dto.brigade.BrigadeCreateDTO;
import com.db.project.api.dto.brigade.BrigadeDTO;
import com.db.project.api.dto.brigade.BrigadeUpdateDTO;

import java.util.List;

public interface BrigadeService {

    BrigadeDTO create(BrigadeCreateDTO brigadeDTO);

    List<BrigadeDTO> getAll();

    BrigadeDTO getById(Integer id);

    Brigade getEntityById(Integer id);

    BrigadeDTO update(Integer id, BrigadeUpdateDTO brigadeDTO);

    void delete(Integer id);
} 