package com.db.project.api.mapper.tester;

import com.db.project.core.model.Tester;
import com.db.project.api.dto.employee.EmployeeShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TesterStatisticsMapper {
    
    @Mapping(target = "id", source = "employee.id")
    @Mapping(target = "name", source = "employee.name")
    @Mapping(target = "position", source = "employee.position")
    EmployeeShortDTO toEmployeeShortDto(Tester tester);
    
    List<EmployeeShortDTO> toEmployeeShortDto(List<Tester> testers);
} 