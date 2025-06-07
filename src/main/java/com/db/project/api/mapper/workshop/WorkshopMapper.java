package com.db.project.api.mapper.workshop;

import com.db.project.core.model.Workshop;
import com.db.project.api.dto.workshop.WorkshopDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkshopMapper {
    WorkshopDTO toDto(Workshop entity);
    List<WorkshopDTO> toDto(List<Workshop> entities);
} 