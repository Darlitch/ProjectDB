package com.db.project.api.controller;

import com.db.project.api.dto.section.SectionCreateDTO;
import com.db.project.api.dto.section.SectionDTO;
import com.db.project.api.dto.section.SectionUpdateDTO;
import com.db.project.api.dto.section.SectionWithHeadDTO;
import com.db.project.api.mapper.section.SectionWithHeadMapper;
import com.db.project.core.repository.SectionRepository;
import com.db.project.core.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;
    private final SectionRepository sectionRepository;
    private final SectionWithHeadMapper sectionWithHeadMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('EDITOR')")
    public SectionDTO create(@Valid @RequestBody SectionCreateDTO sectionDTO) {
        return sectionService.create(sectionDTO);
    }

    @GetMapping
    public List<SectionDTO> getAll() {
        return sectionService.getAll();
    }

    @GetMapping("/{id}")
    public SectionDTO getById(@PathVariable Integer id) {
        return sectionService.getById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public SectionDTO update(@PathVariable Integer id, @Valid @RequestBody SectionUpdateDTO sectionDTO) {
        return sectionService.update(id, sectionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('EDITOR')")
    public void delete(@PathVariable Integer id) {
        sectionService.delete(id);
    }

    @GetMapping("/filter")
    public List<SectionDTO> getByWorkshop(@RequestParam(required = false) Integer workshopId) {
        return sectionService.getByWorkshop(workshopId);
    }
} 