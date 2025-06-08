package com.db.project.api.mapper.attributevalue;

import com.db.project.core.model.AttributeValue;
import com.db.project.core.service.ProductService;
import com.db.project.core.service.ProductAttributeService;
import com.db.project.api.dto.attributevalue.AttributeValueUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProductService.class, ProductAttributeService.class})
public interface AttributeValueUpdateMapper {
    @Mapping(target = "product", source = "productId", qualifiedByName = "getEntityById")
    @Mapping(target = "attribute", source = "attributeId", qualifiedByName = "getEntityById")
    void updateFromDto(AttributeValueUpdateDTO dto, @MappingTarget AttributeValue entity);
} 