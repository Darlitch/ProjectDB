package com.db.project.api.mapper.testequipment;

import com.db.project.core.model.TestEquipment;
import com.db.project.api.dto.testequipment.TestEquipmentUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestEquipmentUpdateMapper {
    void updateFromDto(TestEquipmentUpdateDTO dto, @MappingTarget TestEquipment entity);
} 