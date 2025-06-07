package com.db.project.api.dto.productionprocess;

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
public class ProductionProcessDTO {
    private Integer id;
    private Integer productId;
    private Integer brigadeId;
    private Integer jobId;
    private LocalDate startDate;
    private LocalDate endDate;
} 