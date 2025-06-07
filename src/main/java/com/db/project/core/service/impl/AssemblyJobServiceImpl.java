package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.AssemblyJob;
import com.db.project.core.repository.AssemblyJobRepository;
import com.db.project.core.service.AssemblyJobService;
import com.db.project.core.service.SectionService;
import com.db.project.api.dto.assemblyjob.AssemblyJobCreateDTO;
import com.db.project.api.dto.assemblyjob.AssemblyJobDTO;
import com.db.project.api.dto.assemblyjob.AssemblyJobUpdateDTO;
import com.db.project.api.mapper.assemblyjob.AssemblyJobMapper;
import com.db.project.api.mapper.assemblyjob.AssemblyJobUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssemblyJobServiceImpl implements AssemblyJobService {

    private final AssemblyJobRepository assemblyJobRepository;
    private final AssemblyJobMapper assemblyJobMapper;
    private final AssemblyJobUpdateMapper assemblyJobUpdateMapper;
    private final SectionService sectionService;

    @Override
    @Transactional
    public AssemblyJobDTO create(AssemblyJobCreateDTO jobDTO) {
        AssemblyJob job = AssemblyJob.builder()
                .name(jobDTO.getName())
                .section(sectionService.getEntityById(jobDTO.getSectionId()))
                .build();
        
        return assemblyJobMapper.toDto(assemblyJobRepository.save(job));
    }

    @Override
    public List<AssemblyJobDTO> getAll() {
        return assemblyJobMapper.toDto(assemblyJobRepository.findAll());
    }

    @Override
    public AssemblyJobDTO getById(Integer id) {
        return assemblyJobMapper.toDto(getEntityById(id));
    }

    @Override
    public AssemblyJob getEntityById(Integer id) {
        return assemblyJobRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Assembly job not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public AssemblyJobDTO update(Integer id, AssemblyJobUpdateDTO jobDTO) {
        AssemblyJob job = getEntityById(id);
        assemblyJobUpdateMapper.updateFromDto(jobDTO, job);
        return assemblyJobMapper.toDto(assemblyJobRepository.save(job));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        assemblyJobRepository.delete(getEntityById(id));
    }
} 