package com.db.project.api.mapper.test;

import com.db.project.api.mapper.testequipment.TestEquipmentShortMapper;
import com.db.project.api.mapper.tester.TesterShortMapper;
import com.db.project.core.model.Test;
import com.db.project.api.dto.test.TestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TesterShortMapper.class, TestEquipmentShortMapper.class})
public interface TestMapper {
    @Mapping(target = "equipments", source = "equipment")
    TestDTO toDto(Test entity);
    List<TestDTO> toDto(List<Test> entities);
}