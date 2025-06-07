package com.db.project.api.dto.tester;

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
public class TesterCreateDTO {
    @NotNull(message = "Test ID is required")
    private Integer testId;
    
    @NotNull(message = "Employee ID is required")
    private Integer employeeId;
} 