package com.db.project.api.dto.workshop;

import com.db.project.api.dto.employee.EmployeeShortDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkshopShortDTO {
    private Integer id;
    private String name;
    private EmployeeShortDTO director;
} 