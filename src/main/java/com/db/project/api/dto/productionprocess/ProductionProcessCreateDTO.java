package com.db.project.api.dto.productionprocess;

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
public class ProductionProcessCreateDTO {
    @NotNull(message = "Product ID is required")
    private Integer productId;
    
    @NotNull(message = "Brigade ID is required")
    private Integer brigadeId;
    
    @NotNull(message = "Job ID is required")
    private Integer jobId;

    private LocalDate startDate;
    private LocalDate endDate;
} 