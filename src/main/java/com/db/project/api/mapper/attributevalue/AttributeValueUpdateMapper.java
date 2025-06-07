package com.db.project.api.mapper.attributevalue;

import com.db.project.core.model.AttributeValue;
import com.db.project.api.dto.attributevalue.AttributeValueUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeValueUpdateMapper {
    void updateFromDto(AttributeValueUpdateDTO dto, @MappingTarget AttributeValue entity);
} 