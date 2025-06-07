package com.db.project.core.service;

import com.db.project.core.model.Tester;
import com.db.project.api.dto.tester.TesterCreateDTO;
import com.db.project.api.dto.tester.TesterDTO;
import com.db.project.api.dto.tester.TesterUpdateDTO;

import java.util.List;

public interface TesterService {

    TesterDTO create(TesterCreateDTO createDTO);

    List<TesterDTO> getAll();

    List<TesterDTO> getAllByLabId(Integer labId);

    List<TesterDTO> getAllByEmployeeId(Integer employeeId);

    TesterDTO getById(Integer employeeId, Integer labId);

    void delete(Integer employeeId, Integer labId);

    TesterDTO update(Integer employeeId, Integer labId, TesterUpdateDTO updateDTO);

    // Internal methods for service layer
    Tester getEntityById(Integer testId, Integer employeeId);
} 