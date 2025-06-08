package com.db.project.api.dto.attributevalue;

import com.db.project.api.dto.product.ProductShortDTO;
import com.db.project.api.dto.productattribute.ProductAttributeShortDTO;
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
public class AttributeValueDTO {
    private Integer id;
    private ProductShortDTO product;
    private ProductAttributeShortDTO attribute;
    private String value;
} 