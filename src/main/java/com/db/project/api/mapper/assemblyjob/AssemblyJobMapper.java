package com.db.project.api.mapper.assemblyjob;

import com.db.project.core.model.AssemblyJob;
import com.db.project.api.dto.assemblyjob.AssemblyJobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssemblyJobMapper {
    AssemblyJobDTO toDto(AssemblyJob entity);
    List<AssemblyJobDTO> toDto(List<AssemblyJob> entities);
} 