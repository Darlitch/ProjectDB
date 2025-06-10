package com.db.project.api.mapper.equipment;

import com.db.project.api.dto.equipment.EquipmentShortDTO;
import com.db.project.core.model.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipmentShortMapper {
    EquipmentShortDTO toDto(Equipment entity);
    List<EquipmentShortDTO> toDto(List<Equipment> entities);
} 