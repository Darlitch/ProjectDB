package com.db.project.api.mapper.sectionmaster;

import com.db.project.core.model.SectionMaster;
import com.db.project.api.dto.sectionmaster.SectionMasterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SectionMasterMapper {
    @Mapping(target = "employeeId", source = "id.employeeId")
    @Mapping(target = "sectionId", source = "id.sectionId")
    SectionMasterDTO toDto(SectionMaster entity);
    
    List<SectionMasterDTO> toDto(List<SectionMaster> entities);
}