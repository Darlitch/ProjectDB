package com.db.project.api.dto.assemblyjob;

import com.db.project.api.dto.productionprocess.ProductionProcessShortDTO;
import com.db.project.api.dto.section.SectionShortDTO;
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
public class AssemblyJobDTO {
    private Integer id;
    private String name;
    private SectionShortDTO section;
    private List<ProductionProcessShortDTO> productionProcesses;
} 