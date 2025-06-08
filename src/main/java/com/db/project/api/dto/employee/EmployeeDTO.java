package com.db.project.api.dto.employee;

import com.db.project.api.dto.position.PositionShortDTO;
import com.db.project.api.dto.section.SectionShortDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterShortDTO;
import com.db.project.api.dto.tester.TesterShortDTO;
import com.db.project.api.dto.worker.WorkerShortDTO;
import com.db.project.api.dto.workshop.WorkshopShortDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {
    private Integer id;
    private String name;
    private PositionShortDTO position;
    private LocalDate hireDate;
    private List<WorkshopShortDTO> directedWorkshops;
    private List<SectionShortDTO> headedSections;
    private List<SectionMasterShortDTO> masterSections;
    private List<WorkerShortDTO> workerBrigades;
    private List<TesterShortDTO> testAssignments;
} 