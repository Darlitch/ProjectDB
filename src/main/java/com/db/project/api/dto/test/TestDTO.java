package com.db.project.api.dto.test;

import com.db.project.core.model.enums.TestResult;
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
public class TestDTO {
    private Integer id;
    private Integer productId;
    private Integer labId;
    private TestResult result;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Integer> testerIds;
    private List<Integer> equipmentIds;
} 