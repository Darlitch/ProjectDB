package com.db.project.api.controller;

import com.db.project.api.dto.brigade.BrigadeCreateDTO;
import com.db.project.api.dto.brigade.BrigadeDTO;
import com.db.project.api.dto.brigade.BrigadeUpdateDTO;
import com.db.project.core.service.BrigadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brigades")
@RequiredArgsConstructor
public class BrigadeController {

    private final BrigadeService brigadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrigadeDTO create(@Valid @RequestBody BrigadeCreateDTO brigadeDTO) {
        return brigadeService.create(brigadeDTO);
    }

    @GetMapping
    public List<BrigadeDTO> getAll() {
        return brigadeService.getAll();
    }

    @GetMapping("/{id}")
    public BrigadeDTO getById(@PathVariable Integer id) {
        return brigadeService.getById(id);
    }

    @PatchMapping("/{id}")
    public BrigadeDTO update(@PathVariable Integer id, @Valid @RequestBody BrigadeUpdateDTO brigadeDTO) {
        return brigadeService.update(id, brigadeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        brigadeService.delete(id);
    }

    @GetMapping("/filter")
    public List<BrigadeDTO> getByWorkshopOrSection(
            @RequestParam(required = false) Integer workshopId,
            @RequestParam(required = false) Integer sectionId) {
        return brigadeService.getByWorkshopOrSection(workshopId, sectionId);
    }

    @GetMapping("/by-product/{productId}")
    public List<BrigadeDTO> getByProduct(@PathVariable Integer productId) {
        return brigadeService.getByProduct(productId);
    }
} 