package com.db.project.api.dto.productattribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductAttributeCreateDTO {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Category ID is required")
    private Integer categoryId;
} 