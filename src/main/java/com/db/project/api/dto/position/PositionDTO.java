package com.db.project.api.dto.position;

import com.db.project.core.model.enums.EmployeeCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionDTO {
    private Integer id;
    private EmployeeCategory category;
    private List<Integer> employeeIds;
} 