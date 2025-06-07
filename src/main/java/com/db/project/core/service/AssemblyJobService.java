package com.db.project.core.service;

import com.db.project.core.model.AssemblyJob;
import com.db.project.api.dto.assemblyjob.AssemblyJobCreateDTO;
import com.db.project.api.dto.assemblyjob.AssemblyJobDTO;
import com.db.project.api.dto.assemblyjob.AssemblyJobUpdateDTO;

import java.util.List;

public interface AssemblyJobService {

    AssemblyJobDTO create(AssemblyJobCreateDTO assemblyJobDTO);

    List<AssemblyJobDTO> getAll();

    AssemblyJobDTO getById(Integer id);

    AssemblyJob getEntityById(Integer id);

    AssemblyJobDTO update(Integer id, AssemblyJobUpdateDTO assemblyJobDTO);

    void delete(Integer id);
} 