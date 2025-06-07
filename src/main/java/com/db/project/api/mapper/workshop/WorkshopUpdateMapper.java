package com.db.project.api.mapper.workshop;

import com.db.project.core.model.Workshop;
import com.db.project.api.dto.workshop.WorkshopUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkshopUpdateMapper {
    void updateFromDto(WorkshopUpdateDTO dto, @MappingTarget Workshop entity);
} 