package com.db.project.core.service;

import com.db.project.core.model.SectionMaster;
import com.db.project.api.dto.sectionmaster.SectionMasterCreateDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterDTO;
import com.db.project.api.dto.sectionmaster.SectionMasterUpdateDTO;

import java.util.List;

public interface SectionMasterService {

    SectionMasterDTO create(SectionMasterCreateDTO masterDTO);

    List<SectionMasterDTO> getAll();

//    List<SectionMasterDTO> getAllBySectionId(Integer sectionId);
//
//    List<SectionMasterDTO> getAllByEmployeeId(Integer employeeId);

    SectionMasterDTO getById(Integer sectionId, Integer employeeId);

    void delete(Integer sectionId, Integer employeeId);

    SectionMasterDTO update(Integer sectionId, Integer employeeId, SectionMasterUpdateDTO masterDTO);

    // Internal methods for service layer
    SectionMaster getEntityById(Integer sectionId, Integer employeeId);

    List<SectionMasterDTO> getByWorkshopOrSection(Integer workshopId, Integer sectionId);
} 