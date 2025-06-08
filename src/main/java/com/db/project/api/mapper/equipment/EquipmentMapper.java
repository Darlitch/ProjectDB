package com.db.project.api.mapper.equipment;

import com.db.project.api.mapper.testequipment.TestEquipmentShortMapper;
import com.db.project.core.model.Equipment;
import com.db.project.api.dto.equipment.EquipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TestEquipmentShortMapper.class})
public interface EquipmentMapper {
    @Mapping(target = "testEquipments", source = "testUsages")
    EquipmentDTO toDto(Equipment entity);
    List<EquipmentDTO> toDto(List<Equipment> entities);
} 