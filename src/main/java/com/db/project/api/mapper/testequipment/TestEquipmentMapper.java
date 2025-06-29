package com.db.project.api.mapper.testequipment;

import com.db.project.core.model.TestEquipment;
import com.db.project.api.dto.testequipment.TestEquipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestEquipmentMapper {
    @Mapping(target = "testId", source = "id.testId")
    @Mapping(target = "equipmentId", source = "id.equipmentId")
    TestEquipmentDTO toDto(TestEquipment entity);
    
    List<TestEquipmentDTO> toDto(List<TestEquipment> entities);
} 