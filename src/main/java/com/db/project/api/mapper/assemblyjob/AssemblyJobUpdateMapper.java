package com.db.project.api.mapper.assemblyjob;

import com.db.project.core.model.AssemblyJob;
import com.db.project.api.dto.assemblyjob.AssemblyJobUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssemblyJobUpdateMapper {
    void updateFromDto(AssemblyJobUpdateDTO dto, @MappingTarget AssemblyJob entity);
} 