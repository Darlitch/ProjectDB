package com.db.project.api.dto.section;

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
public class SectionDTO {
    private Integer id;
    private String name;
    private Integer workshopId;
    private Integer headId;
    private List<Integer> brigadeIds;
    private List<Integer> masterIds;
    private List<Integer> assemblyJobIds;
} 