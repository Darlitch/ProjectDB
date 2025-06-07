package com.db.project.api.controller;

import com.db.project.api.dto.testequipment.TestEquipmentCreateDTO;
import com.db.project.api.dto.testequipment.TestEquipmentDTO;
import com.db.project.api.dto.testequipment.TestEquipmentUpdateDTO;
import com.db.project.core.service.TestEquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-equipment")
@RequiredArgsConstructor
public class TestEquipmentController {

    private final TestEquipmentService testEquipmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestEquipmentDTO create(@Valid @RequestBody TestEquipmentCreateDTO equipmentDTO) {
        return testEquipmentService.create(equipmentDTO);
    }

    @GetMapping
    public List<TestEquipmentDTO> getAll() {
        return testEquipmentService.getAll();
    }

    @GetMapping("/by-test/{testId}")
    public List<TestEquipmentDTO> getAllByTestId(@PathVariable Integer testId) {
        return testEquipmentService.getAllByTestId(testId);
    }

    @GetMapping("/by-equipment/{equipmentId}")
    public List<TestEquipmentDTO> getAllByEquipmentId(@PathVariable Integer equipmentId) {
        return testEquipmentService.getAllByEquipmentId(equipmentId);
    }

    @GetMapping("/{testId}/{equipmentId}")
    public TestEquipmentDTO getById(@PathVariable Integer testId, @PathVariable Integer equipmentId) {
        return testEquipmentService.getById(testId, equipmentId);
    }

    @PatchMapping("/{testId}/{equipmentId}")
    public TestEquipmentDTO update(
            @PathVariable Integer testId,
            @PathVariable Integer equipmentId,
            @Valid @RequestBody TestEquipmentUpdateDTO equipmentDTO) {
        return testEquipmentService.update(testId, equipmentId, equipmentDTO);
    }

    @DeleteMapping("/{testId}/{equipmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer testId, @PathVariable Integer equipmentId) {
        testEquipmentService.delete(testId, equipmentId);
    }
} 