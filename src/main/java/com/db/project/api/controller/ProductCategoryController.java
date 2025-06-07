package com.db.project.api.controller;

import com.db.project.api.dto.productcategory.ProductCategoryCreateDTO;
import com.db.project.api.dto.productcategory.ProductCategoryDTO;
import com.db.project.api.dto.productcategory.ProductCategoryUpdateDTO;
import com.db.project.core.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategoryDTO create(@Valid @RequestBody ProductCategoryCreateDTO categoryDTO) {
        return productCategoryService.create(categoryDTO);
    }

    @GetMapping
    public List<ProductCategoryDTO> getAll() {
        return productCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public ProductCategoryDTO getById(@PathVariable Integer id) {
        return productCategoryService.getById(id);
    }

    @PatchMapping("/{id}")
    public ProductCategoryDTO update(@PathVariable Integer id, @Valid @RequestBody ProductCategoryUpdateDTO categoryDTO) {
        return productCategoryService.update(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productCategoryService.delete(id);
    }
} 