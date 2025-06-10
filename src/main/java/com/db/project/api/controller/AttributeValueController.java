package com.db.project.api.controller;

import com.db.project.api.dto.attributevalue.AttributeValueCreateDTO;
import com.db.project.api.dto.attributevalue.AttributeValueDTO;
import com.db.project.api.dto.attributevalue.AttributeValueUpdateDTO;
import com.db.project.core.service.AttributeValueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute-values")
@RequiredArgsConstructor
public class AttributeValueController {

    private final AttributeValueService attributeValueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('EDITOR')")
    public AttributeValueDTO create(@Valid @RequestBody AttributeValueCreateDTO valueDTO) {
        return attributeValueService.create(valueDTO);
    }

    @GetMapping
    public List<AttributeValueDTO> getAll() {
        return attributeValueService.getAll();
    }

    @GetMapping("/{id}")
    public AttributeValueDTO getById(@PathVariable Integer id) {
        return attributeValueService.getById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public AttributeValueDTO update(@PathVariable Integer id, @Valid @RequestBody AttributeValueUpdateDTO valueDTO) {
        return attributeValueService.update(id, valueDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('EDITOR')")
    public void delete(@PathVariable Integer id) {
        attributeValueService.delete(id);
    }
} 