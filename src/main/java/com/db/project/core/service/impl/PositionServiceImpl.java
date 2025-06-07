package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Position;
import com.db.project.core.repository.PositionRepository;
import com.db.project.core.service.PositionService;
import com.db.project.api.dto.position.PositionCreateDTO;
import com.db.project.api.dto.position.PositionDTO;
import com.db.project.api.dto.position.PositionUpdateDTO;
import com.db.project.api.mapper.position.PositionMapper;
import com.db.project.api.mapper.position.PositionUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    private final PositionUpdateMapper positionUpdateMapper;

    @Override
    @Transactional
    public PositionDTO create(PositionCreateDTO positionDTO) {
        Position position = Position.builder()
                .category(positionDTO.getCategory())
                .build();
        
        return positionMapper.toDto(positionRepository.save(position));
    }

    @Override
    public List<PositionDTO> getAll() {
        return positionMapper.toDto(positionRepository.findAll());
    }

    @Override
    public PositionDTO getById(Integer id) {
        return positionMapper.toDto(getEntityById(id));
    }

    @Override
    public Position getEntityById(Integer id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Position not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public PositionDTO update(Integer id, PositionUpdateDTO positionDTO) {
        Position position = getEntityById(id);
        positionUpdateMapper.updateFromDto(positionDTO, position);
        return positionMapper.toDto(positionRepository.save(position));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        positionRepository.delete(getEntityById(id));
    }
} 