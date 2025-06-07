package com.db.project.api.dto.testequipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class TestEquipmentCreateDTO {
    @NotNull(message = "Test ID is required")
    private Integer testId;
    
    @NotNull(message = "Equipment ID is required")
    private Integer equipmentId;
} 