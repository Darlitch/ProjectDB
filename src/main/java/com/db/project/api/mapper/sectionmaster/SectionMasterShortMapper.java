package com.db.project.api.mapper.sectionmaster;

import com.db.project.api.dto.sectionmaster.SectionMasterShortDTO;
import com.db.project.core.model.SectionMaster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SectionMasterShortMapper {
    @Mapping(target = "employeeId", source = "id.employeeId")
    @Mapping(target = "sectionId", source = "id.sectionId")
    SectionMasterShortDTO toDto(SectionMaster entity);
    List<SectionMasterShortDTO> toDto(List<SectionMaster> entities);
} 