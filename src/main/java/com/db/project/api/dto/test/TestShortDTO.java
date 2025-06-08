package com.db.project.api.dto.test;

import com.db.project.api.dto.product.ProductShortDTO;
import com.db.project.api.dto.testlab.TestLabShortDTO;
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
public class TestShortDTO {
    private Integer id;
    private ProductShortDTO product;
    private TestLabShortDTO lab;
    private TestResult result;
    private LocalDate startDate;
    private LocalDate endDate;
} 