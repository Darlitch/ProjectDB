package com.db.project.api.controller;

import com.db.project.api.dto.testlab.TestLabCreateDTO;
import com.db.project.api.dto.testlab.TestLabDTO;
import com.db.project.api.dto.testlab.TestLabShortDTO;
import com.db.project.api.dto.testlab.TestLabUpdateDTO;
import com.db.project.core.service.TestLabService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-labs")
@RequiredArgsConstructor
public class TestLabController {

    private final TestLabService testLabService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestLabDTO create(@Valid @RequestBody TestLabCreateDTO labDTO) {
        return testLabService.create(labDTO);
    }

    @GetMapping
    public List<TestLabDTO> getAll() {
        return testLabService.getAll();
    }

    @GetMapping("/{id}")
    public TestLabDTO getById(@PathVariable Integer id) {
        return testLabService.getById(id);
    }

    @GetMapping("/by-product/{productId}")
    public List<TestLabShortDTO> getAllByProductId(@PathVariable Integer productId) {
        return testLabService.findAllByProductId(productId);
    }

    @PatchMapping("/{id}")
    public TestLabDTO update(@PathVariable Integer id, @Valid @RequestBody TestLabUpdateDTO labDTO) {
        return testLabService.update(id, labDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        testLabService.delete(id);
    }
} 