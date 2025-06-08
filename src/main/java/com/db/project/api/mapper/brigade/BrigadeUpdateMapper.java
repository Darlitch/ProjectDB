package com.db.project.api.mapper.brigade;

import com.db.project.core.model.Brigade;
import com.db.project.core.service.SectionService;
import com.db.project.api.dto.brigade.BrigadeUpdateDTO;
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
public interface BrigadeUpdateMapper {
    @Mapping(target = "section", source = "sectionId", qualifiedByName = "getEntityById")
    void updateFromDto(BrigadeUpdateDTO dto, @MappingTarget Brigade entity);
} 