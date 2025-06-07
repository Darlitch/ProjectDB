package com.db.project.api.mapper.brigade;

import com.db.project.core.model.Brigade;
import com.db.project.api.dto.brigade.BrigadeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrigadeMapper {
    BrigadeDTO toDto(Brigade entity);
    List<BrigadeDTO> toDto(List<Brigade> entities);
}