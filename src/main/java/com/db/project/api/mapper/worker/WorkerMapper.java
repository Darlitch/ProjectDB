package com.db.project.api.mapper.worker;

import com.db.project.core.model.Worker;
import com.db.project.api.dto.worker.WorkerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkerMapper {
    @Mapping(target = "brigadeId", source = "id.brigadeId")
    @Mapping(target = "employeeId", source = "id.employeeId")
    WorkerDTO toDto(Worker entity);
    
    List<WorkerDTO> toDto(List<Worker> entities);
}