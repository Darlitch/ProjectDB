package com.db.project.api.mapper.worker;

import com.db.project.core.model.Worker;
import com.db.project.api.dto.worker.WorkerShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkerShortMapper {
    @Mapping(target = "brigadeId", source = "id.brigadeId")
    @Mapping(target = "employeeId", source = "id.employeeId")
    WorkerShortDTO toDto(Worker entity);
    
    List<WorkerShortDTO> toDto(List<Worker> entities);
} 