package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Section;
import com.db.project.core.repository.SectionRepository;
import com.db.project.core.service.SectionService;
import com.db.project.core.service.WorkshopService;
import com.db.project.core.service.EmployeeService;
import com.db.project.api.dto.section.SectionCreateDTO;
import com.db.project.api.dto.section.SectionDTO;
import com.db.project.api.dto.section.SectionUpdateDTO;
import com.db.project.api.mapper.section.SectionMapper;
import com.db.project.api.mapper.section.SectionUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SectionUpdateMapper sectionUpdateMapper;
    private final WorkshopService workshopService;
    private final EmployeeService employeeService;

    @Override
    @Transactional
    public SectionDTO create(SectionCreateDTO sectionDTO) {
        Section section = Section.builder()
                .name(sectionDTO.getName())
                .workshop(workshopService.getEntityById(sectionDTO.getWorkshopId()))
                .head(sectionDTO.getHeadId() != null ? employeeService.getEntityById(sectionDTO.getHeadId()) : null)
                .build();
        
        return sectionMapper.toDto(sectionRepository.save(section));
    }

    @Override
    public List<SectionDTO> getAll() {
        return sectionMapper.toDto(sectionRepository.findAll());
    }

    @Override
    public SectionDTO getById(Integer id) {
        return sectionMapper.toDto(getEntityById(id));
    }

    @Override
    public Section getEntityById(Integer id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Section not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public SectionDTO update(Integer id, SectionUpdateDTO sectionDTO) {
        Section section = getEntityById(id);
        sectionUpdateMapper.updateFromDto(sectionDTO, section);
        return sectionMapper.toDto(sectionRepository.save(section));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        sectionRepository.delete(getEntityById(id));
    }
} 