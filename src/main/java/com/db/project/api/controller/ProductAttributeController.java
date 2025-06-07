package com.db.project.api.controller;

import com.db.project.api.dto.productattribute.ProductAttributeCreateDTO;
import com.db.project.api.dto.productattribute.ProductAttributeDTO;
import com.db.project.api.dto.productattribute.ProductAttributeUpdateDTO;
import com.db.project.core.service.ProductAttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAttributeDTO create(@Valid @RequestBody ProductAttributeCreateDTO attributeDTO) {
        return productAttributeService.create(attributeDTO);
    }

    @GetMapping
    public List<ProductAttributeDTO> getAll() {
        return productAttributeService.getAll();
    }

    @GetMapping("/{id}")
    public ProductAttributeDTO getById(@PathVariable Integer id) {
        return productAttributeService.getById(id);
    }

    @PatchMapping("/{id}")
    public ProductAttributeDTO update(@PathVariable Integer id, @Valid @RequestBody ProductAttributeUpdateDTO attributeDTO) {
        return productAttributeService.update(id, attributeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productAttributeService.delete(id);
    }
} 