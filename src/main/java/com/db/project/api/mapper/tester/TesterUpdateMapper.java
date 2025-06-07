package com.db.project.api.mapper.tester;

import com.db.project.core.model.Tester;
import com.db.project.api.dto.tester.TesterUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TesterUpdateMapper {
    void updateFromDto(TesterUpdateDTO dto, @MappingTarget Tester entity);
} 