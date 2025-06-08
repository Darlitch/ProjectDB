package com.db.project.api.dto.workshop;

import com.db.project.api.dto.employee.EmployeeShortDTO;
import com.db.project.api.dto.product.ProductShortDTO;
import com.db.project.api.dto.section.SectionShortDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkshopDTO {
    private Integer id;
    private String name;
    private EmployeeShortDTO director;
    private List<SectionShortDTO> sections;
    private List<ProductShortDTO> products;
} 