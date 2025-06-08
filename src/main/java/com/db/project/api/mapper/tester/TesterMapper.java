package com.db.project.api.mapper.tester;

import com.db.project.core.model.Tester;
import com.db.project.api.dto.tester.TesterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TesterMapper {
    @Mapping(target = "testId", source = "id.testId")
    @Mapping(target = "employeeId", source = "id.employeeId")
    TesterDTO toDto(Tester entity);
    
    List<TesterDTO> toDto(List<Tester> entities);
}