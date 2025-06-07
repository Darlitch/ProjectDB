package com.db.project.api.controller;

import com.db.project.api.dto.position.PositionCreateDTO;
import com.db.project.api.dto.position.PositionDTO;
import com.db.project.api.dto.position.PositionUpdateDTO;
import com.db.project.core.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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