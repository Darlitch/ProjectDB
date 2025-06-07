package com.db.project.api.mapper.equipment;

import com.db.project.core.model.Equipment;
import com.db.project.api.dto.equipment.EquipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipmentMapper {
    EquipmentDTO toDto(Equipment entity);
    List<EquipmentDTO> toDto(List<Equipment> entities);
} 