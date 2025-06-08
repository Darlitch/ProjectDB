package com.db.project.api.dto.productionprocess;

import com.db.project.api.dto.assemblyjob.AssemblyJobShortDTO;
import com.db.project.api.dto.brigade.BrigadeShortDTO;
import com.db.project.api.dto.product.ProductShortDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ProductionProcessDTO {
    private Integer id;
    private ProductShortDTO product;
    private BrigadeShortDTO brigade;
    private AssemblyJobShortDTO job;
    private LocalDate startDate;
    private LocalDate endDate;
} 