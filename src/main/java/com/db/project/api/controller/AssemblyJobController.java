package com.db.project.api.controller;

import com.db.project.api.dto.assemblyjob.AssemblyJobCreateDTO;
import com.db.project.api.dto.assemblyjob.AssemblyJobDTO;
import com.db.project.api.dto.assemblyjob.AssemblyJobUpdateDTO;
import com.db.project.core.service.AssemblyJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assembly-jobs")
@RequiredArgsConstructor
public class AssemblyJobController {

    private final AssemblyJobService assemblyJobService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('EDITOR')")
    public AssemblyJobDTO create(@Valid @RequestBody AssemblyJobCreateDTO jobDTO) {
        return assemblyJobService.create(jobDTO);
    }

    @GetMapping
    public List<AssemblyJobDTO> getAll() {
        return assemblyJobService.getAll();
    }

    @GetMapping("/{id}")
    public AssemblyJobDTO getById(@PathVariable Integer id) {
        return assemblyJobService.getById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public AssemblyJobDTO update(@PathVariable Integer id, @Valid @RequestBody AssemblyJobUpdateDTO jobDTO) {
        return assemblyJobService.update(id, jobDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('EDITOR')")
    public void delete(@PathVariable Integer id) {
        assemblyJobService.delete(id);
    }
} 