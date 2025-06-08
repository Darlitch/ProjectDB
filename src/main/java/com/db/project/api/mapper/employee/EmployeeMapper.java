package com.db.project.api.mapper.employee;

import com.db.project.api.mapper.sectionmaster.SectionMasterShortMapper;
import com.db.project.api.mapper.worker.WorkerShortMapper;
import com.db.project.api.mapper.tester.TesterShortMapper;
import com.db.project.core.model.Employee;
import com.db.project.api.dto.employee.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
            SectionMasterShortMapper.class,
            WorkerShortMapper.class,
            TesterShortMapper.class
        })
public interface EmployeeMapper {
    EmployeeDTO toDto(Employee entity);
    List<EmployeeDTO> toDto(List<Employee> entities);
} 