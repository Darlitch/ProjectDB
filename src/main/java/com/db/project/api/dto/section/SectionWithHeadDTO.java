package com.db.project.api.dto.section;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionWithHeadDTO {
    private Integer id;
    private String name;
    private String workshopName;
    private String headName;
} 