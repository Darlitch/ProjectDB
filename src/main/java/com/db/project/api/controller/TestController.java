package com.db.project.api.controller;

import com.db.project.api.dto.test.TestCreateDTO;
import com.db.project.api.dto.test.TestDTO;
import com.db.project.api.dto.test.TestUpdateDTO;
import com.db.project.core.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestDTO create(@Valid @RequestBody TestCreateDTO testDTO) {
        return testService.create(testDTO);
    }

    @GetMapping
    public List<TestDTO> getAll() {
        return testService.getAll();
    }

    @GetMapping("/{id}")
    public TestDTO getById(@PathVariable Integer id) {
        return testService.getById(id);
    }

    @PatchMapping("/{id}")
    public TestDTO update(@PathVariable Integer id, @Valid @RequestBody TestUpdateDTO testDTO) {
        return testService.update(id, testDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        testService.delete(id);
    }
} 