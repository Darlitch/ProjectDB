package com.db.project.api.dto.productattribute;

import com.db.project.api.dto.attributevalue.AttributeValueShortDTO;
import com.db.project.api.dto.productcategory.ProductCategoryShortDTO;
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
public class ProductAttributeDTO {
    private Integer id;
    private String name;
    private ProductCategoryShortDTO category;
    private List<AttributeValueShortDTO> attributeValues;
} 