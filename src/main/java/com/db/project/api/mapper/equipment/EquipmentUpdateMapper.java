package com.db.project.api.mapper.equipment;

import com.db.project.core.model.Equipment;
import com.db.project.core.service.TestLabService;
import com.db.project.api.dto.equipment.EquipmentUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {TestLabService.class})
public interface EquipmentUpdateMapper {
    @Mapping(target = "lab", source = "labId", qualifiedByName = "getEntityById")
    void updateFromDto(EquipmentUpdateDTO dto, @MappingTarget Equipment entity);
} 