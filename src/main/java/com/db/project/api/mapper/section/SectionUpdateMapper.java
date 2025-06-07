package com.db.project.api.mapper.section;

import com.db.project.core.model.Section;
import com.db.project.api.dto.section.SectionUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SectionUpdateMapper {
    void updateFromDto(SectionUpdateDTO dto, @MappingTarget Section entity);
} 