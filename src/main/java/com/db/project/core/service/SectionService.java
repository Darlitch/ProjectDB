package com.db.project.core.service;

import com.db.project.core.model.Section;
import com.db.project.api.dto.section.SectionCreateDTO;
import com.db.project.api.dto.section.SectionDTO;
import com.db.project.api.dto.section.SectionUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface SectionService {

    SectionDTO create(SectionCreateDTO sectionDTO);

    List<SectionDTO> getAll();

    SectionDTO getById(Integer id);

    @Named("getEntityById")
    Section getEntityById(Integer id);

    SectionDTO update(Integer id, SectionUpdateDTO sectionDTO);

    void delete(Integer id);

    List<SectionDTO> getByWorkshop(Integer workshopId);
} 