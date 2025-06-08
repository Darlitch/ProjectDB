package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.TestEquipment;
import com.db.project.core.model.TestEquipmentId;
import com.db.project.core.repository.TestEquipmentRepository;
import com.db.project.core.service.TestEquipmentService;
import com.db.project.core.service.TestService;
import com.db.project.core.service.EquipmentService;
import com.db.project.api.dto.testequipment.TestEquipmentCreateDTO;
import com.db.project.api.dto.testequipment.TestEquipmentDTO;
import com.db.project.api.dto.testequipment.TestEquipmentUpdateDTO;
import com.db.project.api.mapper.testequipment.TestEquipmentMapper;
import com.db.project.api.mapper.testequipment.TestEquipmentUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestEquipmentServiceImpl implements TestEquipmentService {

    private final TestEquipmentRepository testEquipmentRepository;
    private final TestEquipmentMapper testEquipmentMapper;
    private final TestEquipmentUpdateMapper testEquipmentUpdateMapper;
    private final TestService testService;
    private final EquipmentService equipmentService;

    @Override
    @Transactional
    public TestEquipmentDTO create(TestEquipmentCreateDTO equipmentDTO) {
        TestEquipment equipment = TestEquipment.builder()
                .id(new TestEquipmentId(equipmentDTO.getTestId(), equipmentDTO.getEquipmentId()))
                .test(testService.getEntityById(equipmentDTO.getTestId()))
                .equipment(equipmentService.getEntityById(equipmentDTO.getEquipmentId()))
                .build();
        
        return testEquipmentMapper.toDto(testEquipmentRepository.save(equipment));
    }

    @Override
    public List<TestEquipmentDTO> getAll() {
        return testEquipmentMapper.toDto(testEquipmentRepository.findAll());
    }

//    @Override
//    public List<TestEquipmentDTO> getAllByTestId(Integer testId) {
//        return testEquipmentMapper.toDto(testEquipmentRepository.findAllByTestId(testId));
//    }
//
//    @Override
//    public List<TestEquipmentDTO> getAllByEquipmentId(Integer equipmentId) {
//        return testEquipmentMapper.toDto(testEquipmentRepository.findAllByEquipmentId(equipmentId));
//    }

    @Override
    public TestEquipmentDTO getById(Integer testId, Integer equipmentId) {
        return testEquipmentMapper.toDto(getEntityById(testId, equipmentId));
    }

    @Override
    public TestEquipment getEntityById(Integer testId, Integer equipmentId) {
        return testEquipmentRepository.findById(new TestEquipmentId(testId, equipmentId))
                .orElseThrow(() -> new ServiceException(
                    String.format("Test equipment not found with testId: %d and equipmentId: %d", 
                        testId, equipmentId), 
                    ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public TestEquipmentDTO update(Integer testId, Integer equipmentId, TestEquipmentUpdateDTO equipmentDTO) {
        TestEquipment equipment = getEntityById(testId, equipmentId);
        testEquipmentUpdateMapper.updateFromDto(equipmentDTO, equipment);
        return testEquipmentMapper.toDto(testEquipmentRepository.save(equipment));
    }

    @Override
    @Transactional
    public void delete(Integer testId, Integer equipmentId) {
        testEquipmentRepository.delete(getEntityById(testId, equipmentId));
    }
}