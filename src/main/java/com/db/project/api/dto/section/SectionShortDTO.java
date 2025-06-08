package com.db.project.api.dto.section;

import com.db.project.api.dto.employee.EmployeeShortDTO;
import com.db.project.api.dto.workshop.WorkshopShortDTO;
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
public class SectionShortDTO {
    private Integer id;
    private String name;
    private WorkshopShortDTO workshop;
    private EmployeeShortDTO head;
} 