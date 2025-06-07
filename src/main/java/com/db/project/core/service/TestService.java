package com.db.project.core.service;

import com.db.project.core.model.Test;
import com.db.project.api.dto.test.TestCreateDTO;
import com.db.project.api.dto.test.TestDTO;
import com.db.project.api.dto.test.TestUpdateDTO;

import java.util.List;

public interface TestService {

    TestDTO create(TestCreateDTO testDTO);

    List<TestDTO> getAll();

    TestDTO getById(Integer id);

    Test getEntityById(Integer id);

    TestDTO update(Integer id, TestUpdateDTO testDTO);

    void delete(Integer id);
} 