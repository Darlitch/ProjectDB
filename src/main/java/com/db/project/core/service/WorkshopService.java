package com.db.project.core.service;

import com.db.project.core.model.Workshop;
import com.db.project.api.dto.workshop.WorkshopCreateDTO;
import com.db.project.api.dto.workshop.WorkshopDTO;
import com.db.project.api.dto.workshop.WorkshopUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface WorkshopService {

    WorkshopDTO create(WorkshopCreateDTO workshopDTO);

    List<WorkshopDTO> getAll();

    WorkshopDTO getById(Integer id);

    @Named("getEntityById")
    Workshop getEntityById(Integer id);

    WorkshopDTO update(Integer id, WorkshopUpdateDTO workshopDTO);

    void delete(Integer id);
} 