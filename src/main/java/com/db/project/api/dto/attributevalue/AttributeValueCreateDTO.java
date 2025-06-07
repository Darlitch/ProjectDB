package com.db.project.api.dto.attributevalue;

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
public class AttributeValueCreateDTO {
    @NotNull(message = "Product ID is required")
    private Integer productId;
    
    @NotNull(message = "Attribute ID is required")
    private Integer attributeId;
    
    @NotBlank(message = "Value is required")
    private String value;
} 