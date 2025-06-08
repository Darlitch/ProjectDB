package com.db.project.api.mapper.section;

import com.db.project.api.mapper.sectionmaster.SectionMasterShortMapper;
import com.db.project.core.model.Section;
import com.db.project.api.dto.section.SectionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {SectionMasterShortMapper.class})
public interface SectionMapper {
    SectionDTO toDto(Section entity);
    List<SectionDTO> toDto(List<Section> entities);
} 