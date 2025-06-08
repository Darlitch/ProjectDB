package com.db.project.api.mapper.brigade;

import com.db.project.core.model.Brigade;
import com.db.project.api.dto.brigade.BrigadeDTO;
import com.db.project.api.mapper.worker.WorkerShortMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {WorkerShortMapper.class})
public interface BrigadeMapper {
//    @Mapping(target = "sectionId", source = "section.id")
//    @Mapping(target = "workerIds", expression = "java(entity.getWorkers().stream().map(worker -> worker.getId().getEmployeeId()).toList())")
//    @Mapping(target = "productionProcessIds", expression = "java(entity.getProductionProcesses().stream().map(process -> process.getId()).toList())")
    BrigadeDTO toDto(Brigade entity);
    
    List<BrigadeDTO> toDto(List<Brigade> entities);
}