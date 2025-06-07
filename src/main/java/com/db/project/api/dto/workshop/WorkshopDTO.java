package com.db.project.api.dto.workshop;

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
public class WorkshopDTO {
    private Integer id;
    private String name;
    private Integer directorId;
    private List<Integer> sectionIds;
    private List<Integer> productIds;
} 