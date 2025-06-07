package com.db.project.api.dto.test;

import com.db.project.core.model.enums.TestResult;
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
public class TestUpdateDTO {
    private Integer productId;
    private Integer labId;
    private TestResult result;
    private LocalDate startDate;
    private LocalDate endDate;
} 