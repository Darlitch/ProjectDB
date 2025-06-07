package com.db.project.api.mapper.employee;

import com.db.project.core.model.Employee;
import com.db.project.api.dto.employee.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
    EmployeeDTO toDto(Employee entity);
    List<EmployeeDTO> toDto(List<Employee> entities);
} 