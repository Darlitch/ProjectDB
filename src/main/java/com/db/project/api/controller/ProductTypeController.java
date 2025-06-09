package com.db.project.api.controller;

import com.db.project.api.dto.producttype.ProductTypeCreateDTO;
import com.db.project.api.dto.producttype.ProductTypeDTO;
import com.db.project.api.dto.producttype.ProductTypeListDTO;
import com.db.project.api.dto.producttype.ProductTypeUpdateDTO;
import com.db.project.core.service.ProductTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-types")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductTypeDTO create(@Valid @RequestBody ProductTypeCreateDTO productTypeDTO) {
        return productTypeService.create(productTypeDTO);
    }

    @GetMapping
    public List<ProductTypeDTO> getAll() {
        return productTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ProductTypeDTO getById(@PathVariable Integer id) {
        return productTypeService.getById(id);
    }

    @PatchMapping("/{id}")
    public ProductTypeDTO update(@PathVariable Integer id, @Valid @RequestBody ProductTypeUpdateDTO productTypeDTO) {
        return productTypeService.update(id, productTypeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productTypeService.delete(id);
    }

    //Получить перечень видов изделий по категории
    @GetMapping("/by-category/{categoryId}")
    public List<ProductTypeListDTO> getTypesByCategory(@PathVariable Integer categoryId) {
        return productTypeService.getTypesByCategory(categoryId);
    }

    // Получить перечень видов изделий, собираемых в указанном цехе
    @GetMapping("/by-workshop/{workshopId}")
    public List<ProductTypeListDTO> getTypesByWorkshop(@PathVariable Integer workshopId) {
        return productTypeService.getTypesByWorkshop(workshopId);
    }

    //Получить перечень видов изделий по цеху и категории
    @GetMapping("/by-workshop/{workshopId}/category/{categoryId}")
    public List<ProductTypeListDTO> getTypesByWorkshopAndCategory(
            @PathVariable Integer workshopId,
            @PathVariable Integer categoryId) {
        return productTypeService.getTypesByWorkshopAndCategory(workshopId, categoryId);
    }
} 