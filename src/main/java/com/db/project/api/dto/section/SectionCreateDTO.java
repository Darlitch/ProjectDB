package com.db.project.api.dto.section;

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
public class SectionCreateDTO {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Workshop ID is required")
    private Integer workshopId;
    
    private Integer headId;
} 