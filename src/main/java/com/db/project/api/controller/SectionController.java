package com.db.project.api.controller;

import com.db.project.api.dto.section.SectionCreateDTO;
import com.db.project.api.dto.section.SectionDTO;
import com.db.project.api.dto.section.SectionUpdateDTO;
import com.db.project.core.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public SectionDTO update(@PathVariable Integer id, @Valid @RequestBody SectionUpdateDTO sectionDTO) {
        return sectionService.update(id, sectionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        sectionService.delete(id);
    }
} 