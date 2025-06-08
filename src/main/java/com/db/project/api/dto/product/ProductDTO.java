package com.db.project.api.dto.product;

import com.db.project.api.dto.attributevalue.AttributeValueShortDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessShortDTO;
import com.db.project.api.dto.producttype.ProductTypeShortDTO;
import com.db.project.api.dto.test.TestShortDTO;
import com.db.project.api.dto.workshop.WorkshopShortDTO;
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
public class ProductDTO {
    private Integer id;
    private String serialNum;
    private ProductTypeShortDTO type;
    private WorkshopShortDTO workshop;
    private List<AttributeValueShortDTO> attributeValues;
    private List<ProductionProcessShortDTO> productionProcesses;
    private List<TestShortDTO> tests;
} 