package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Workshop;
import com.db.project.core.repository.WorkshopRepository;
import com.db.project.core.service.WorkshopService;
import com.db.project.core.service.EmployeeService;
import com.db.project.api.dto.workshop.WorkshopCreateDTO;
import com.db.project.api.dto.workshop.WorkshopDTO;
import com.db.project.api.dto.workshop.WorkshopUpdateDTO;
import com.db.project.api.mapper.workshop.WorkshopMapper;
import com.db.project.api.mapper.workshop.WorkshopUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final WorkshopMapper workshopMapper;
    private final WorkshopUpdateMapper workshopUpdateMapper;
    private final EmployeeService employeeService;

    @Override
    @Transactional
    public WorkshopDTO create(WorkshopCreateDTO workshopDTO) {
        Workshop workshop = Workshop.builder()
                .name(workshopDTO.getName())
                .director(workshopDTO.getDirectorId() != null ? employeeService.getEntityById(workshopDTO.getDirectorId()) : null)
                .build();
        
        return workshopMapper.toDto(workshopRepository.save(workshop));
    }

    @Override
    public List<WorkshopDTO> getAll() {
        return workshopMapper.toDto(workshopRepository.findAll());
    }

    @Override
    public WorkshopDTO getById(Integer id) {
        return workshopMapper.toDto(getEntityById(id));
    }

    @Override
    public Workshop getEntityById(Integer id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Workshop not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public WorkshopDTO update(Integer id, WorkshopUpdateDTO workshopDTO) {
        Workshop workshop = getEntityById(id);
        workshopUpdateMapper.updateFromDto(workshopDTO, workshop);
        return workshopMapper.toDto(workshopRepository.save(workshop));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        workshopRepository.delete(getEntityById(id));
    }
} 