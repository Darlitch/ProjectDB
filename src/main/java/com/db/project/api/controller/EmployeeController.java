package com.db.project.api.controller;

import com.db.project.api.dto.employee.EmployeeCreateDTO;
import com.db.project.api.dto.employee.EmployeeDTO;
import com.db.project.api.dto.employee.EmployeeUpdateDTO;
import com.db.project.core.service.EmployeeService;
import com.db.project.core.model.enums.EmployeeCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO create(@Valid @RequestBody EmployeeCreateDTO employeeDTO) {
        return employeeService.create(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @PatchMapping("/{id}")
    public EmployeeDTO update(@PathVariable Integer id, @Valid @RequestBody EmployeeUpdateDTO employeeDTO) {
        return employeeService.update(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        employeeService.delete(id);
    }

    @GetMapping("/filter")
    public List<EmployeeDTO> getByWorkshopAndCategory(
            @RequestParam(required = false) Integer workshopId,
            @RequestParam(required = false) EmployeeCategory category) {
        return employeeService.getByWorkshopAndCategory(workshopId, category);
    }
} 