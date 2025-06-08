package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Worker;
import com.db.project.core.model.WorkerId;
import com.db.project.core.repository.WorkerRepository;
import com.db.project.core.service.WorkerService;
import com.db.project.core.service.BrigadeService;
import com.db.project.core.service.EmployeeService;
import com.db.project.api.dto.worker.WorkerCreateDTO;
import com.db.project.api.dto.worker.WorkerDTO;
import com.db.project.api.dto.worker.WorkerUpdateDTO;
import com.db.project.api.mapper.worker.WorkerMapper;
import com.db.project.api.mapper.worker.WorkerUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;
    private final WorkerUpdateMapper workerUpdateMapper;
    private final BrigadeService brigadeService;
    private final EmployeeService employeeService;

    @Override
    @Transactional
    public WorkerDTO create(WorkerCreateDTO workerDTO) {
        Worker worker = Worker.builder()
                .id(new WorkerId(workerDTO.getBrigadeId(), workerDTO.getEmployeeId()))
                .brigade(brigadeService.getEntityById(workerDTO.getBrigadeId()))
                .employee(employeeService.getEntityById(workerDTO.getEmployeeId()))
                .isForeman(workerDTO.getIsForeman())
                .build();
        
        return workerMapper.toDto(workerRepository.save(worker));
    }

    @Override
    public List<WorkerDTO> getAll() {
        return workerMapper.toDto(workerRepository.findAll());
    }

//    @Override
//    public List<WorkerDTO> getAllByBrigadeId(Integer brigadeId) {
//        return workerMapper.toDto(workerRepository.findAllByBrigadeId(brigadeId));
//    }
//
//    @Override
//    public List<WorkerDTO> getAllByEmployeeId(Integer employeeId) {
//        return workerMapper.toDto(workerRepository.findAllByEmployeeId(employeeId));
//    }

    @Override
    public WorkerDTO getById(Integer brigadeId, Integer employeeId) {
        return workerMapper.toDto(getEntityById(brigadeId, employeeId));
    }

    @Override
    public Worker getEntityById(Integer brigadeId, Integer employeeId) {
        return workerRepository.findById(new WorkerId(brigadeId, employeeId))
                .orElseThrow(() -> new ServiceException(
                    String.format("Worker not found with brigadeId: %d and employeeId: %d", 
                        brigadeId, employeeId), 
                    ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public WorkerDTO update(Integer brigadeId, Integer employeeId, WorkerUpdateDTO workerDTO) {
        Worker worker = getEntityById(brigadeId, employeeId);
        workerUpdateMapper.updateFromDto(workerDTO, worker);
        return workerMapper.toDto(workerRepository.save(worker));
    }

    @Override
    @Transactional
    public void delete(Integer brigadeId, Integer employeeId) {
        workerRepository.delete(getEntityById(brigadeId, employeeId));
    }
} 