package com.db.project.api.mapper.testequipment;

import com.db.project.api.dto.testequipment.TestEquipmentShortDTO;
import com.db.project.core.model.TestEquipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestEquipmentShortMapper {
    @Mapping(target = "testId", source = "id.testId")
    @Mapping(target = "equipmentId", source = "id.equipmentId")
    TestEquipmentShortDTO toDto(TestEquipment entity);
    List<TestEquipmentShortDTO> toDto(List<TestEquipment> entities);
} 