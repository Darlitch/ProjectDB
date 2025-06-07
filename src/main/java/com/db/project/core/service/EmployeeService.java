package com.db.project.core.service;

import com.db.project.core.model.Employee;
import com.db.project.api.dto.employee.EmployeeCreateDTO;
import com.db.project.api.dto.employee.EmployeeDTO;
import com.db.project.api.dto.employee.EmployeeUpdateDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO create(EmployeeCreateDTO employeeDTO);

    List<EmployeeDTO> getAll();

    EmployeeDTO getById(Integer id);

    Employee getEntityById(Integer id);

    EmployeeDTO update(Integer id, EmployeeUpdateDTO employeeDTO);

    void delete(Integer id);
} 