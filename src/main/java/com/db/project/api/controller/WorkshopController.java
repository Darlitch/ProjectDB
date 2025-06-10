package com.db.project.api.controller;

import com.db.project.api.dto.workshop.WorkshopCreateDTO;
import com.db.project.api.dto.workshop.WorkshopDTO;
import com.db.project.api.dto.workshop.WorkshopUpdateDTO;
import com.db.project.core.service.WorkshopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
@RequiredArgsConstructor
public class WorkshopController {

    private final WorkshopService workshopService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('EDITOR')")
    public WorkshopDTO create(@Valid @RequestBody WorkshopCreateDTO workshopDTO) {
        return workshopService.create(workshopDTO);
    }

    @GetMapping
    public List<WorkshopDTO> getAll() {
        return workshopService.getAll();
    }

    @GetMapping("/{id}")
    public WorkshopDTO getById(@PathVariable Integer id) {
        return workshopService.getById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public WorkshopDTO update(@PathVariable Integer id, @Valid @RequestBody WorkshopUpdateDTO workshopDTO) {
        return workshopService.update(id, workshopDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('EDITOR')")
    public void delete(@PathVariable Integer id) {
        workshopService.delete(id);
    }
} 