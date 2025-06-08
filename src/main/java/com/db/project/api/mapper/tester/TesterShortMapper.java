package com.db.project.api.mapper.tester;

import com.db.project.api.dto.tester.TesterShortDTO;
import com.db.project.core.model.Tester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TesterShortMapper {
    @Mapping(target = "testId", source = "id.testId")
    @Mapping(target = "employeeId", source = "id.employeeId")
    TesterShortDTO toDto(Tester entity);
    List<TesterShortDTO> toDto(List<Tester> entities);
} 