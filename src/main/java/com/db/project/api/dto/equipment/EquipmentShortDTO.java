package com.db.project.api.dto.equipment;

import com.db.project.api.dto.testlab.TestLabShortDTO;
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
public class EquipmentShortDTO {
    private Integer id;
    private String name;
    private TestLabShortDTO lab;
} 