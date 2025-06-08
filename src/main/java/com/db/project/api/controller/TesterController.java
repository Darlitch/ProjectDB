package com.db.project.api.controller;

import com.db.project.api.dto.tester.TesterCreateDTO;
import com.db.project.api.dto.tester.TesterDTO;
import com.db.project.api.dto.tester.TesterUpdateDTO;
import com.db.project.core.service.TesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/by-lab/{labId}")
//    public List<TesterDTO> getAllByLabId(@PathVariable Integer labId) {
//        return testerService.getAllByLabId(labId);
//    }
//
//    @GetMapping("/by-employee/{employeeId}")
//    public List<TesterDTO> getAllByEmployeeId(@PathVariable Integer employeeId) {
//        return testerService.getAllByEmployeeId(employeeId);
//    }

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