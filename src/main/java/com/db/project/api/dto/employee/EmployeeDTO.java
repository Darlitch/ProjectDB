package com.db.project.api.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {
    private Integer id;
    private String name;
    private Integer positionId;
    private LocalDate hireDate;
    private List<Integer> directedWorkshopIds;
    private List<Integer> headedSectionIds;
    private List<Integer> masterSectionIds;
    private List<Integer> workerBrigadeIds;
    private List<Integer> testAssignmentIds;
} 