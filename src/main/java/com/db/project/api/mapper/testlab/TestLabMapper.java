package com.db.project.api.mapper.testlab;

import com.db.project.core.model.TestLab;
import com.db.project.api.dto.testlab.TestLabDTO;
import com.db.project.api.dto.testlab.TestLabShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestLabMapper {
    TestLabDTO toDto(TestLab entity);
    List<TestLabDTO> toDto(List<TestLab> entities);
    
    TestLabShortDTO toShortDto(TestLab entity);
    List<TestLabShortDTO> toShortDto(List<TestLab> entities);
} 