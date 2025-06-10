package com.db.project.api.controller;

import com.db.project.api.dto.employee.EmployeeShortDTO;
import com.db.project.api.dto.tester.TesterCreateDTO;
import com.db.project.api.dto.tester.TesterDTO;
import com.db.project.api.dto.tester.TesterUpdateDTO;
import com.db.project.core.service.TesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/testers")
@RequiredArgsConstructor
public class TesterController {

    private final TesterService testerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TesterDTO create(@Valid @RequestBody TesterCreateDTO testerDTO) {
        return testerService.create(testerDTO);
    }

    @GetMapping
    public List<TesterDTO> getAll() {
        return testerService.getAll();
    }

    @GetMapping("/by-parameters")
    public List<EmployeeShortDTO> getTestersByParameters(
            @RequestParam(required = false) Integer labId,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return testerService.getTestersByParameters(labId, productId, categoryId, startDate, endDate);
    }

    @GetMapping("/{employeeId}/{labId}")
    public TesterDTO getById(@PathVariable Integer employeeId, @PathVariable Integer labId) {
        return testerService.getById(employeeId, labId);
    }

    @PatchMapping("/{employeeId}/{labId}")
    public TesterDTO update(
            @PathVariable Integer employeeId,
            @PathVariable Integer labId,
            @Valid @RequestBody TesterUpdateDTO testerDTO) {
        return testerService.update(employeeId, labId, testerDTO);
    }

    @DeleteMapping("/{employeeId}/{labId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer employeeId, @PathVariable Integer labId) {
        testerService.delete(employeeId, labId);
    }
} 