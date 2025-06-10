package com.db.project.api.controller;

import com.db.project.api.dto.position.PositionCreateDTO;
import com.db.project.api.dto.position.PositionDTO;
import com.db.project.api.dto.position.PositionUpdateDTO;
import com.db.project.core.service.PositionService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PositionDTO create(@Valid @RequestBody PositionCreateDTO positionDTO) {
        return positionService.create(positionDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('EDITOR')")
    public List<PositionDTO> getAll() {
        return positionService.getAll();
    }

    @GetMapping("/{id}")
    public PositionDTO getById(@PathVariable Integer id) {
        return positionService.getById(id);
    }

    @PatchMapping("/{id}")
    public PositionDTO update(@PathVariable Integer id, @Valid @RequestBody PositionUpdateDTO positionDTO) {
        return positionService.update(id, positionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        positionService.delete(id);
    }
} 