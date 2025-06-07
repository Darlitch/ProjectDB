package com.db.project.api.mapper.test;

import com.db.project.core.model.Test;
import com.db.project.api.dto.test.TestUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestUpdateMapper {
    void updateFromDto(TestUpdateDTO dto, @MappingTarget Test entity);
} 