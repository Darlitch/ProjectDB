package com.db.project.api.dto.producttype;

import com.db.project.api.dto.product.ProductShortDTO;
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
public class ProductTypeDTO {
    private Integer id;
    private String name;
    private ProductCategoryShortDTO category;
    private List<ProductShortDTO> products;
} 