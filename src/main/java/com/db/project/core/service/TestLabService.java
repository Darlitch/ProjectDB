package com.db.project.core.service;

import com.db.project.core.model.TestLab;
import com.db.project.api.dto.testlab.TestLabCreateDTO;
import com.db.project.api.dto.testlab.TestLabDTO;
import com.db.project.api.dto.testlab.TestLabUpdateDTO;
import org.mapstruct.Named;

import java.util.List;

public interface TestLabService {

    TestLabDTO create(TestLabCreateDTO testLabDTO);

    List<TestLabDTO> getAll();

    TestLabDTO getById(Integer id);

    @Named("getEntityById")
    TestLab getEntityById(Integer id);

    TestLabDTO update(Integer id, TestLabUpdateDTO testLabDTO);

    void delete(Integer id);
} 