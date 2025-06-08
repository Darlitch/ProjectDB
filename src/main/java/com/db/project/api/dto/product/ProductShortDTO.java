package com.db.project.api.dto.product;

import com.db.project.api.dto.producttype.ProductTypeShortDTO;
import com.db.project.api.dto.workshop.WorkshopShortDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductShortDTO {
    private Integer id;
    private String serialNum;
    private ProductTypeShortDTO type;
    private WorkshopShortDTO workshop;
} 