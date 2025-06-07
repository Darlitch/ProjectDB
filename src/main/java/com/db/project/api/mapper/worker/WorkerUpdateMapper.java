package com.db.project.api.mapper.worker;

import com.db.project.core.model.Worker;
import com.db.project.api.dto.worker.WorkerUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkerUpdateMapper {
    void updateFromDto(WorkerUpdateDTO dto, @MappingTarget Worker entity);
} 