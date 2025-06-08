package com.db.project.api.mapper.assemblyjob;

import com.db.project.core.model.AssemblyJob;
import com.db.project.core.service.SectionService;
import com.db.project.api.dto.assemblyjob.AssemblyJobUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {SectionService.class})
public interface AssemblyJobUpdateMapper {
    @Mapping(target = "section", source = "sectionId", qualifiedByName = "getEntityById")
    void updateFromDto(AssemblyJobUpdateDTO dto, @MappingTarget AssemblyJob entity);
} 