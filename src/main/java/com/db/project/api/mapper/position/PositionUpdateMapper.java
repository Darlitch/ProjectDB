package com.db.project.api.mapper.position;

import com.db.project.core.model.Position;
import com.db.project.api.dto.position.PositionUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionUpdateMapper {
    void updateFromDto(PositionUpdateDTO dto, @MappingTarget Position entity);
} 