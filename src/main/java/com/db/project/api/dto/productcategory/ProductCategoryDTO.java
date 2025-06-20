package com.db.project.api.dto.productcategory;

import com.db.project.api.dto.productattribute.ProductAttributeShortDTO;
import com.db.project.api.dto.producttype.ProductTypeShortDTO;
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
public class ProductCategoryDTO {
    private Integer id;
    private String name;
    private List<ProductTypeShortDTO> types;
    private List<ProductAttributeShortDTO> attributes;
} 