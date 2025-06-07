package com.db.project.api.mapper.brigade;

import com.db.project.core.model.Brigade;
import com.db.project.api.dto.brigade.BrigadeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrigadeUpdateMapper {
    void updateFromDto(BrigadeUpdateDTO dto, @MappingTarget Brigade entity);
} 