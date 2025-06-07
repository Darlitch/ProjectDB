package com.db.project.api.mapper.sectionmaster;

import com.db.project.core.model.SectionMaster;
import com.db.project.api.dto.sectionmaster.SectionMasterUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SectionMasterUpdateMapper {
    void updateFromDto(SectionMasterUpdateDTO dto, @MappingTarget SectionMaster entity);
} 