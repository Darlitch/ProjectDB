package com.db.project.api.mapper.attributevalue;

import com.db.project.core.model.AttributeValue;
import com.db.project.api.dto.attributevalue.AttributeValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttributeValueMapper {
    AttributeValueDTO toDto(AttributeValue entity);
    List<AttributeValueDTO> toDto(List<AttributeValue> entities);
} 