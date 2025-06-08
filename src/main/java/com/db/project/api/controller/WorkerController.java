package com.db.project.api.controller;

import com.db.project.api.dto.worker.WorkerCreateDTO;
import com.db.project.api.dto.worker.WorkerDTO;
import com.db.project.api.dto.worker.WorkerUpdateDTO;
import com.db.project.core.service.WorkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workers")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkerDTO create(@Valid @RequestBody WorkerCreateDTO workerDTO) {
        return workerService.create(workerDTO);
    }

    @GetMapping
    public List<WorkerDTO> getAll() {
        return workerService.getAll();
    }

//    @GetMapping("/by-brigade/{brigadeId}")
//    public List<WorkerDTO> getAllByBrigadeId(@PathVariable Integer brigadeId) {
//        return workerService.getAllByBrigadeId(brigadeId);
//    }
//
//    @GetMapping("/by-employee/{employeeId}")
//    public List<WorkerDTO> getAllByEmployeeId(@PathVariable Integer employeeId) {
//        return workerService.getAllByEmployeeId(employeeId);
//    }

    @GetMapping("/{brigadeId}/{employeeId}")
    public WorkerDTO getById(@PathVariable Integer brigadeId, @PathVariable Integer employeeId) {
        return workerService.getById(brigadeId, employeeId);
    }

    @PatchMapping("/{brigadeId}/{employeeId}")
    public WorkerDTO update(
            @PathVariable Integer brigadeId,
            @PathVariable Integer employeeId,
            @Valid @RequestBody WorkerUpdateDTO workerDTO) {
        return workerService.update(brigadeId, employeeId, workerDTO);
    }

    @DeleteMapping("/{brigadeId}/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer brigadeId, @PathVariable Integer employeeId) {
        workerService.delete(brigadeId, employeeId);
    }
} 