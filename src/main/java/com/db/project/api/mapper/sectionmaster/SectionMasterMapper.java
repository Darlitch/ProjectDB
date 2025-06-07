package com.db.project.api.mapper.sectionmaster;

import com.db.project.core.model.SectionMaster;
import com.db.project.api.dto.sectionmaster.SectionMasterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SectionMasterMapper {
    SectionMasterDTO toDto(SectionMaster entity);
    List<SectionMasterDTO> toDto(List<SectionMaster> entities);
} 