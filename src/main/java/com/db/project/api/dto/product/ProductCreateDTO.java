package com.db.project.api.dto.product;

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
public class ProductCreateDTO {
    @NotBlank(message = "Serial number is required")
    private String serialNum;
    
    @NotNull(message = "Type ID is required")
    private Integer typeId;
    
    @NotNull(message = "Workshop ID is required")
    private Integer workshopId;
} 