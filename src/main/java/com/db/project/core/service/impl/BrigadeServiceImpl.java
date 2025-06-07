package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Brigade;
import com.db.project.core.repository.BrigadeRepository;
import com.db.project.core.service.BrigadeService;
import com.db.project.core.service.SectionService;
import com.db.project.api.dto.brigade.BrigadeCreateDTO;
import com.db.project.api.dto.brigade.BrigadeDTO;
import com.db.project.api.dto.brigade.BrigadeUpdateDTO;
import com.db.project.api.mapper.brigade.BrigadeMapper;
import com.db.project.api.mapper.brigade.BrigadeUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrigadeServiceImpl implements BrigadeService {

    private final BrigadeRepository brigadeRepository;
    private final BrigadeMapper brigadeMapper;
    private final BrigadeUpdateMapper brigadeUpdateMapper;
    private final SectionService sectionService;

    @Override
    @Transactional
    public BrigadeDTO create(BrigadeCreateDTO brigadeDTO) {
        Brigade brigade = Brigade.builder()
                .name(brigadeDTO.getName())
                .section(sectionService.getEntityById(brigadeDTO.getSectionId()))
                .build();
        
        return brigadeMapper.toDto(brigadeRepository.save(brigade));
    }

    @Override
    public List<BrigadeDTO> getAll() {
        return brigadeMapper.toDto(brigadeRepository.findAll());
    }

    @Override
    public BrigadeDTO getById(Integer id) {
        return brigadeMapper.toDto(getEntityById(id));
    }

    @Override
    public Brigade getEntityById(Integer id) {
        return brigadeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Brigade not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public BrigadeDTO update(Integer id, BrigadeUpdateDTO brigadeDTO) {
        Brigade brigade = getEntityById(id);
        brigadeUpdateMapper.updateFromDto(brigadeDTO, brigade);
        return brigadeMapper.toDto(brigadeRepository.save(brigade));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        brigadeRepository.delete(getEntityById(id));
    }
} 