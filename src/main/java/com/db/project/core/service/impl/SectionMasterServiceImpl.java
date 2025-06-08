package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.SectionMaster;
import com.db.project.core.model.SectionMasterId;
import com.db.project.core.repository.SectionMasterRepository;
import com.db.project.core.service.SectionMasterService;
import com.db.project.core.service.SectionService;
import com.db.project.core.service.EmployeeService;
import com.db.project.api.dto.sectionmaster.SectionMasterCreateDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterUpdateDTO;
import com.db.project.api.mapper.sectionmaster.SectionMasterMapper;
import com.db.project.api.mapper.sectionmaster.SectionMasterUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SectionMasterServiceImpl implements SectionMasterService {

    private final SectionMasterRepository sectionMasterRepository;
    private final SectionMasterMapper sectionMasterMapper;
    private final SectionMasterUpdateMapper sectionMasterUpdateMapper;
    private final SectionService sectionService;
    private final EmployeeService employeeService;

    @Override
    @Transactional
    public SectionMasterDTO create(SectionMasterCreateDTO masterDTO) {
        SectionMaster master = SectionMaster.builder()
                .section(sectionService.getEntityById(masterDTO.getSectionId()))
                .employee(employeeService.getEntityById(masterDTO.getEmployeeId()))
                .build();
        
        return sectionMasterMapper.toDto(sectionMasterRepository.save(master));
    }

    @Override
    public List<SectionMasterDTO> getAll() {
        return sectionMasterMapper.toDto(sectionMasterRepository.findAll());
    }

//    @Override
//    public List<SectionMasterDTO> getAllBySectionId(Integer sectionId) {
//        return sectionMasterMapper.toDto(sectionMasterRepository.findAllBySectionId(sectionId));
//    }
//
//    @Override
//    public List<SectionMasterDTO> getAllByEmployeeId(Integer employeeId) {
//        return sectionMasterMapper.toDto(sectionMasterRepository.findAllByEmployeeId(employeeId));
//    }

    @Override
    public SectionMasterDTO getById(Integer sectionId, Integer employeeId) {
        return sectionMasterMapper.toDto(getEntityById(sectionId, employeeId));
    }

    @Override
    public SectionMaster getEntityById(Integer sectionId, Integer employeeId) {
        return sectionMasterRepository.findById(new SectionMasterId(sectionId, employeeId))
                .orElseThrow(() -> new ServiceException(
                    String.format("Section master not found with sectionId: %d and employeeId: %d", 
                        sectionId, employeeId), 
                    ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public SectionMasterDTO update(Integer sectionId, Integer employeeId, SectionMasterUpdateDTO masterDTO) {
        SectionMaster master = getEntityById(sectionId, employeeId);
        sectionMasterUpdateMapper.updateFromDto(masterDTO, master);
        return sectionMasterMapper.toDto(sectionMasterRepository.save(master));
    }

    @Override
    @Transactional
    public void delete(Integer sectionId, Integer employeeId) {
        sectionMasterRepository.delete(getEntityById(sectionId, employeeId));
    }
} 