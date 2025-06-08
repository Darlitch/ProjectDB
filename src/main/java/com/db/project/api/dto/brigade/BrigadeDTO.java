package com.db.project.api.dto.brigade;

import com.db.project.api.dto.productionprocess.ProductionProcessShortDTO;
import com.db.project.api.dto.section.SectionShortDTO;
import com.db.project.api.dto.worker.WorkerShortDTO;
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
public class BrigadeDTO {
    private Integer id;
    private String name;
    private SectionShortDTO section;
    private List<WorkerShortDTO> workers;
    private List<ProductionProcessShortDTO> productionProcesses;
} 