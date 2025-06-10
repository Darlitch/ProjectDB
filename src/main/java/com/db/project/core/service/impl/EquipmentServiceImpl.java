package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Equipment;
import com.db.project.core.repository.EquipmentRepository;
import com.db.project.core.service.EquipmentService;
import com.db.project.core.service.TestLabService;
import com.db.project.api.dto.equipment.EquipmentCreateDTO;
import com.db.project.api.dto.equipment.EquipmentDTO;
import com.db.project.api.dto.equipment.EquipmentUpdateDTO;
import com.db.project.api.dto.equipment.EquipmentShortDTO;
import com.db.project.api.mapper.equipment.EquipmentMapper;
import com.db.project.api.mapper.equipment.EquipmentUpdateMapper;
import com.db.project.api.mapper.equipment.EquipmentShortMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentUpdateMapper equipmentUpdateMapper;
    private final EquipmentShortMapper equipmentShortMapper;
    private final TestLabService testLabService;

    @Override
    @Transactional
    public EquipmentDTO create(EquipmentCreateDTO equipmentDTO) {
        Equipment equipment = Equipment.builder()
                .name(equipmentDTO.getName())
                .lab(testLabService.getEntityById(equipmentDTO.getLabId()))
                .build();
        
        return equipmentMapper.toDto(equipmentRepository.save(equipment));
    }

    @Override
    public List<EquipmentDTO> getAll() {
        return equipmentMapper.toDto(equipmentRepository.findAll());
    }

    @Override
    public EquipmentDTO getById(Integer id) {
        return equipmentMapper.toDto(getEntityById(id));
    }

    @Override
    public Equipment getEntityById(Integer id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Equipment not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public EquipmentDTO update(Integer id, EquipmentUpdateDTO equipmentDTO) {
        Equipment equipment = getEntityById(id);
        equipmentUpdateMapper.updateFromDto(equipmentDTO, equipment);
        return equipmentMapper.toDto(equipmentRepository.save(equipment));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        equipmentRepository.delete(getEntityById(id));
    }

    @Override
    public List<EquipmentShortDTO> findEquipmentByTestParameters(
            Integer productId,
            Integer categoryId,
            Integer labId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return equipmentShortMapper.toDto(
                equipmentRepository.findEquipmentByTestParameters(
                        productId, categoryId, labId, startDate, endDate
                )
        );
    }
}