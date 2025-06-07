package com.db.project.api.mapper.testlab;

import com.db.project.core.model.TestLab;
import com.db.project.api.dto.testlab.TestLabUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestLabUpdateMapper {
    void updateFromDto(TestLabUpdateDTO dto, @MappingTarget TestLab entity);
} 