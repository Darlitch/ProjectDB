package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Tester;
import com.db.project.core.model.TesterId;
import com.db.project.core.repository.TesterRepository;
import com.db.project.core.service.TesterService;
import com.db.project.core.service.TestService;
import com.db.project.core.service.EmployeeService;
import com.db.project.api.dto.tester.TesterCreateDTO;
import com.db.project.api.dto.tester.TesterDTO;
import com.db.project.api.dto.tester.TesterUpdateDTO;
import com.db.project.api.mapper.tester.TesterMapper;
import com.db.project.api.mapper.tester.TesterUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TesterServiceImpl implements TesterService {

    private final TesterRepository testerRepository;
    private final TesterMapper testerMapper;
    private final TesterUpdateMapper testerUpdateMapper;
    private final TestService testService;
    private final EmployeeService employeeService;

    @Override
    @Transactional
    public TesterDTO create(TesterCreateDTO testerDTO) {
        Tester tester = Tester.builder()
                .id(new TesterId(testerDTO.getTestId(), testerDTO.getEmployeeId()))
                .test(testService.getEntityById(testerDTO.getTestId()))
                .employee(employeeService.getEntityById(testerDTO.getEmployeeId()))
                .build();
        
        return testerMapper.toDto(testerRepository.save(tester));
    }

    @Override
    public List<TesterDTO> getAll() {
        return testerMapper.toDto(testerRepository.findAll());
    }

//    @Override
//    public List<TesterDTO> getAllByLabId(Integer labId) {
//        return testerMapper.toDto(testerRepository.findAllByLabId(labId));
//    }
//
//    @Override
//    public List<TesterDTO> getAllByEmployeeId(Integer employeeId) {
//        return testerMapper.toDto(testerRepository.findAllByEmployeeId(employeeId));
//    }

    @Override
    public TesterDTO getById(Integer testId, Integer employeeId) {
        return testerMapper.toDto(getEntityById(testId, employeeId));
    }

    @Override
    public Tester getEntityById(Integer testId, Integer employeeId) {
        return testerRepository.findById(new TesterId(testId, employeeId))
                .orElseThrow(() -> new ServiceException(
                    String.format("Tester not found with testId: %d and employeeId: %d", 
                        testId, employeeId), 
                    ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public TesterDTO update(Integer testId, Integer employeeId, TesterUpdateDTO testerDTO) {
        Tester tester = getEntityById(testId, employeeId);
        testerUpdateMapper.updateFromDto(testerDTO, tester);
        return testerMapper.toDto(testerRepository.save(tester));
    }

    @Override
    @Transactional
    public void delete(Integer testId, Integer employeeId) {
        testerRepository.delete(getEntityById(testId, employeeId));
    }
} 