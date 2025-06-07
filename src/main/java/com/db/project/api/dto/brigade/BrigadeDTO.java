package com.db.project.api.dto.brigade;

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
    private Integer sectionId;
    private List<Integer> workerIds;
    private List<Integer> productionProcessIds;
} 