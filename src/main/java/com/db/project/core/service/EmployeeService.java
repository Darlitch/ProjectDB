package com.db.project.core.service;

import com.db.project.core.model.Employee;
import com.db.project.api.dto.employee.EmployeeCreateDTO;
import com.db.project.api.dto.employee.EmployeeDTO;
import com.db.project.api.dto.employee.EmployeeUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO create(EmployeeCreateDTO employeeDTO);

    List<EmployeeDTO> getAll();

    EmployeeDTO getById(Integer id);

    @Named("getEntityById")
    Employee getEntityById(Integer id);

    EmployeeDTO update(Integer id, EmployeeUpdateDTO employeeDTO);

    void delete(Integer id);
} 