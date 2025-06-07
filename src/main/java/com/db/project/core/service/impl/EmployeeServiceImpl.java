package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Employee;
import com.db.project.core.repository.EmployeeRepository;
import com.db.project.core.service.EmployeeService;
import com.db.project.core.service.PositionService;
import com.db.project.api.dto.employee.EmployeeCreateDTO;
import com.db.project.api.dto.employee.EmployeeDTO;
import com.db.project.api.dto.employee.EmployeeUpdateDTO;
import com.db.project.api.mapper.employee.EmployeeMapper;
import com.db.project.api.mapper.employee.EmployeeUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeUpdateMapper employeeUpdateMapper;
    private final PositionService positionService;

    @Override
    @Transactional
    public EmployeeDTO create(EmployeeCreateDTO employeeDTO) {
        Employee employee = Employee.builder()
                .name(employeeDTO.getName())
                .position(positionService.getEntityById(employeeDTO.getPositionId()))
                .hireDate(employeeDTO.getHireDate())
                .build();
        
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO getById(Integer id) {
        return employeeMapper.toDto(getEntityById(id));
    }

    @Override
    public Employee getEntityById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Employee not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public EmployeeDTO update(Integer id, EmployeeUpdateDTO employeeDTO) {
        Employee employee = getEntityById(id);
        employeeUpdateMapper.updateFromDto(employeeDTO, employee);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        employeeRepository.delete(getEntityById(id));
    }
} 