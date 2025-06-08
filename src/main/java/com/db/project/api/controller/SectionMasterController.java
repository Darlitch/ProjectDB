package com.db.project.api.controller;

import com.db.project.api.dto.sectionmaster.SectionMasterCreateDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterUpdateDTO;
import com.db.project.core.service.SectionMasterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section-masters")
@RequiredArgsConstructor
public class SectionMasterController {

    private final SectionMasterService sectionMasterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SectionMasterDTO create(@Valid @RequestBody SectionMasterCreateDTO masterDTO) {
        return sectionMasterService.create(masterDTO);
    }

    @GetMapping
    public List<SectionMasterDTO> getAll() {
        return sectionMasterService.getAll();
    }

//    @GetMapping("/by-section/{sectionId}")
//    public List<SectionMasterDTO> getAllBySectionId(@PathVariable Integer sectionId) {
//        return sectionMasterService.getAllBySectionId(sectionId);
//    }
//
//    @GetMapping("/by-employee/{employeeId}")
//    public List<SectionMasterDTO> getAllByEmployeeId(@PathVariable Integer employeeId) {
//        return sectionMasterService.getAllByEmployeeId(employeeId);
//    }

    @GetMapping("/{employeeId}/{sectionId}")
    public SectionMasterDTO getById(@PathVariable Integer employeeId, @PathVariable Integer sectionId) {
        return sectionMasterService.getById(sectionId, employeeId);
    }

    @PatchMapping("/{employeeId}/{sectionId}")
    public SectionMasterDTO update(
            @PathVariable Integer employeeId,
            @PathVariable Integer sectionId,
            @Valid @RequestBody SectionMasterUpdateDTO masterDTO) {
        return sectionMasterService.update(sectionId, employeeId, masterDTO);
    }

    @DeleteMapping("/{employeeId}/{sectionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer employeeId, @PathVariable Integer sectionId) {
        sectionMasterService.delete(sectionId, employeeId);
    }
} 