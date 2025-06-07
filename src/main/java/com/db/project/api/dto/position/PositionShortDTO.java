package com.db.project.api.dto.position;

import com.db.project.core.model.enums.EmployeeCategory;
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
public class PositionShortDTO {
    private Integer id;
    private EmployeeCategory category;
} 