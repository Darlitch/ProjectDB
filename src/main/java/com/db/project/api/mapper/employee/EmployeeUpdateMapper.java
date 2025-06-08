package com.db.project.api.mapper.employee;

import com.db.project.core.model.Employee;
import com.db.project.core.service.PositionService;
import com.db.project.api.dto.employee.EmployeeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PositionService.class})
public interface EmployeeUpdateMapper {
    @Mapping(target = "position", source = "positionId", qualifiedByName = "getEntityById")
    void updateFromDto(EmployeeUpdateDTO dto, @MappingTarget Employee entity);
} 