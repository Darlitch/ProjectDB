package com.db.project.api.mapper.position;

import com.db.project.core.model.Position;
import com.db.project.api.dto.position.PositionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PositionMapper {
    PositionDTO toDto(Position entity);
    List<PositionDTO> toDto(List<Position> entities);
} 