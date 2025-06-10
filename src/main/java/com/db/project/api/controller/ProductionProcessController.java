package com.db.project.api.controller;

import com.db.project.api.dto.product.ProductShortDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessCreateDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessUpdateDTO;
import com.db.project.core.service.ProductionProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/production-processes")
@RequiredArgsConstructor
public class ProductionProcessController {

    private final ProductionProcessService productionProcessService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductionProcessDTO create(@Valid @RequestBody ProductionProcessCreateDTO processDTO) {
        return productionProcessService.create(processDTO);
    }

    @GetMapping
    public List<ProductionProcessDTO> getAll() {
        return productionProcessService.getAll();
    }

    @GetMapping("/{id}")
    public ProductionProcessDTO getById(@PathVariable Integer id) {
        return productionProcessService.getById(id);
    }

    @GetMapping("/by-product/{productId}")
    public List<ProductionProcessDTO> getByProduct(@PathVariable Integer productId) {
        return productionProcessService.getByProduct(productId);
    }

    @GetMapping("/current-products")
    public List<ProductShortDTO> getCurrentProductsInProduction(
            @RequestParam(required = false) Integer workshopId,
            @RequestParam(required = false) Integer sectionId,
            @RequestParam(required = false) Integer categoryId) {
        return productionProcessService.getCurrentProductsInProduction(workshopId, sectionId, categoryId);
    }

    @PatchMapping("/{id}")
    public ProductionProcessDTO update(@PathVariable Integer id, @Valid @RequestBody ProductionProcessUpdateDTO processDTO) {
        return productionProcessService.update(id, processDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productionProcessService.delete(id);
    }
} 