package com.db.project.core.service;

import com.db.project.core.model.TestEquipment;
import com.db.project.core.model.TestEquipmentId;
import com.db.project.api.dto.testequipment.TestEquipmentCreateDTO;
import com.db.project.api.dto.testequipment.TestEquipmentDTO;
import com.db.project.api.dto.testequipment.TestEquipmentUpdateDTO;

import java.util.List;

public interface TestEquipmentService {
    TestEquipmentDTO create(TestEquipmentCreateDTO equipmentDTO);
    
    List<TestEquipmentDTO> getAll();
    
    List<TestEquipmentDTO> getAllByTestId(Integer testId);
    
    List<TestEquipmentDTO> getAllByEquipmentId(Integer equipmentId);
    
    TestEquipmentDTO getById(Integer testId, Integer equipmentId);
    
    void delete(Integer testId, Integer equipmentId);
    
    TestEquipmentDTO update(Integer testId, Integer equipmentId, TestEquipmentUpdateDTO equipmentDTO);

    // Internal methods for service layer
    TestEquipment getEntityById(Integer testId, Integer equipmentId);
} 