package com.db.project.api.dto.section;

import com.db.project.api.dto.assemblyjob.AssemblyJobShortDTO;
import com.db.project.api.dto.brigade.BrigadeShortDTO;
import com.db.project.api.dto.employee.EmployeeShortDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterShortDTO;
import com.db.project.api.dto.workshop.WorkshopShortDTO;
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
    private WorkshopShortDTO workshop;
    private EmployeeShortDTO head;
    private List<BrigadeShortDTO> brigades;
    private List<SectionMasterShortDTO> masters;
    private List<AssemblyJobShortDTO> assemblyJobs;
} 