package com.db.project.api.dto.test;

import com.db.project.core.model.enums.TestResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
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
public class TestCreateDTO {
    @NotNull(message = "Product ID is required")
    private Integer productId;
    
    @NotNull(message = "Lab ID is required")
    private Integer labId;
    
    @NotNull(message = "Result is required")
    private TestResult result;
    
    @Builder.Default
    private LocalDate startDate = LocalDate.now();

    @Builder.Default
    private LocalDate endDate = null;
} 