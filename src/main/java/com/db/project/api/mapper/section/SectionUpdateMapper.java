package com.db.project.api.mapper.section;

import com.db.project.core.model.Section;
import com.db.project.core.service.WorkshopService;
import com.db.project.core.service.EmployeeService;
import com.db.project.api.dto.section.SectionUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {WorkshopService.class, EmployeeService.class})
public interface SectionUpdateMapper {
    @Mapping(target = "workshop", source = "workshopId", qualifiedByName = "getEntityById")
    @Mapping(target = "head", source = "headId", qualifiedByName = "getEntityById")
    void updateFromDto(SectionUpdateDTO dto, @MappingTarget Section entity);
} 