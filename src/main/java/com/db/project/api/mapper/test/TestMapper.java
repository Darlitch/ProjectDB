package com.db.project.api.mapper.test;

import com.db.project.core.model.Test;
import com.db.project.api.dto.test.TestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestMapper {
    TestDTO toDto(Test entity);
    List<TestDTO> toDto(List<Test> entities);
} 