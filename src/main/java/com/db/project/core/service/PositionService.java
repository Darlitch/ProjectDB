package com.db.project.core.service;

import com.db.project.core.model.Position;
import com.db.project.api.dto.position.PositionCreateDTO;
import com.db.project.api.dto.position.PositionDTO;
import com.db.project.api.dto.position.PositionUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface PositionService {

    PositionDTO create(PositionCreateDTO positionDTO);

    List<PositionDTO> getAll();

    PositionDTO getById(Integer id);

    @Named("getEntityById")
    Position getEntityById(Integer id);

    PositionDTO update(Integer id, PositionUpdateDTO positionDTO);

    void delete(Integer id);
} 