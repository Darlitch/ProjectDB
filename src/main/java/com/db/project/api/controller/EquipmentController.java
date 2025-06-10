package com.db.project.api.controller;

import com.db.project.api.dto.equipment.EquipmentCreateDTO;
import com.db.project.api.dto.equipment.EquipmentDTO;
import com.db.project.api.dto.equipment.EquipmentUpdateDTO;
import com.db.project.api.dto.equipment.EquipmentShortDTO;
import com.db.project.core.service.EquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('EDITOR')")
    public EquipmentDTO create(@Valid @RequestBody EquipmentCreateDTO equipmentDTO) {
        return equipmentService.create(equipmentDTO);
    }

    @GetMapping
    public List<EquipmentDTO> getAll() {
        return equipmentService.getAll();
    }

    @GetMapping("/{id}")
    public EquipmentDTO getById(@PathVariable Integer id) {
        return equipmentService.getById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public EquipmentDTO update(@PathVariable Integer id, @Valid @RequestBody EquipmentUpdateDTO equipmentDTO) {
        return equipmentService.update(id, equipmentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('EDITOR')")
    public void delete(@PathVariable Integer id) {
        equipmentService.delete(id);
    }

    @GetMapping("/statistics")
    public List<EquipmentShortDTO> findEquipmentByTestParameters(
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer labId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return equipmentService.findEquipmentByTestParameters(productId, categoryId, labId, startDate, endDate);
    }
} 