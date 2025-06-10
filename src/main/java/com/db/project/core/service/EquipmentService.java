package com.db.project.core.service;

import com.db.project.core.model.Equipment;
import com.db.project.api.dto.equipment.EquipmentCreateDTO;
import com.db.project.api.dto.equipment.EquipmentDTO;
import com.db.project.api.dto.equipment.EquipmentUpdateDTO;
import com.db.project.api.dto.equipment.EquipmentShortDTO;

import java.time.LocalDate;
import java.util.List;

public interface EquipmentService {

    EquipmentDTO create(EquipmentCreateDTO equipmentDTO);

    List<EquipmentDTO> getAll();

    EquipmentDTO getById(Integer id);

    Equipment getEntityById(Integer id);

    EquipmentDTO update(Integer id, EquipmentUpdateDTO equipmentDTO);

    void delete(Integer id);

    List<EquipmentShortDTO> findEquipmentByTestParameters(
            Integer productId,
            Integer categoryId,
            Integer labId,
            LocalDate startDate,
            LocalDate endDate
    );
} 