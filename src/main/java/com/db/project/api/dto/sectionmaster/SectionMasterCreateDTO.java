package com.db.project.api.dto.sectionmaster;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionMasterCreateDTO {
    @NotNull(message = "Employee ID is required")
    private Integer employeeId;
    
    @NotNull(message = "Section ID is required")
    private Integer sectionId;
} 