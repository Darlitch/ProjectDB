package com.db.project.api.dto.employee;

import com.db.project.api.dto.position.PositionShortDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeShortDTO {
    private Integer id;
    private String name;
    private PositionShortDTO position;
    private LocalDate hireDate;
} 