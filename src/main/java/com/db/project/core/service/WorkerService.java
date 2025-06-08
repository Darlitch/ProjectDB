package com.db.project.core.service;

import com.db.project.core.model.Worker;
import com.db.project.api.dto.worker.WorkerCreateDTO;
import com.db.project.api.dto.worker.WorkerDTO;
import com.db.project.api.dto.worker.WorkerUpdateDTO;

import java.util.List;

public interface WorkerService {

    WorkerDTO create(WorkerCreateDTO workerDTO);

    List<WorkerDTO> getAll();

//    List<WorkerDTO> getAllByBrigadeId(Integer brigadeId);
//
//    List<WorkerDTO> getAllByEmployeeId(Integer employeeId);

    WorkerDTO getById(Integer brigadeId, Integer employeeId);

    void delete(Integer brigadeId, Integer employeeId);

    WorkerDTO update(Integer brigadeId, Integer employeeId, WorkerUpdateDTO workerDTO);

    // Internal methods for service layer
    Worker getEntityById(Integer brigadeId, Integer employeeId);
} 