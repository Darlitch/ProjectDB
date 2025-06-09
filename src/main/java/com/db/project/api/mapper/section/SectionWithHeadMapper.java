package com.db.project.api.mapper.section;

import com.db.project.core.model.Section;
import com.db.project.api.dto.section.SectionWithHeadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionWithHeadMapper {
    
    @Mapping(target = "workshopName", source = "workshop.name")
    @Mapping(target = "headName", source = "head.name")
    SectionWithHeadDTO toDto(Section section);
    
    List<SectionWithHeadDTO> toDto(List<Section> sections);
} 