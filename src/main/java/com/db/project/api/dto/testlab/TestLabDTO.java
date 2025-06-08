package com.db.project.api.dto.testlab;

import com.db.project.api.dto.equipment.EquipmentShortDTO;
import com.db.project.api.dto.test.TestShortDTO;
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
public class TestLabDTO {
    private Integer id;
    private String name;
    private List<EquipmentShortDTO> equipments;
    private List<TestShortDTO> tests;
} 