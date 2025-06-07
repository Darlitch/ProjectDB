package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.TestLab;
import com.db.project.core.repository.TestLabRepository;
import com.db.project.core.service.TestLabService;
import com.db.project.api.dto.testlab.TestLabCreateDTO;
import com.db.project.api.dto.testlab.TestLabDTO;
import com.db.project.api.dto.testlab.TestLabUpdateDTO;
import com.db.project.api.mapper.testlab.TestLabMapper;
import com.db.project.api.mapper.testlab.TestLabUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestLabServiceImpl implements TestLabService {

    private final TestLabRepository testLabRepository;
    private final TestLabMapper testLabMapper;
    private final TestLabUpdateMapper testLabUpdateMapper;

    @Override
    @Transactional
    public TestLabDTO create(TestLabCreateDTO labDTO) {
        TestLab lab = TestLab.builder()
                .name(labDTO.getName())
                .build();
        
        return testLabMapper.toDto(testLabRepository.save(lab));
    }

    @Override
    public List<TestLabDTO> getAll() {
        return testLabMapper.toDto(testLabRepository.findAll());
    }

    @Override
    public TestLabDTO getById(Integer id) {
        return testLabMapper.toDto(getEntityById(id));
    }

    @Override
    public TestLab getEntityById(Integer id) {
        return testLabRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Test lab not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public TestLabDTO update(Integer id, TestLabUpdateDTO labDTO) {
        TestLab lab = getEntityById(id);
        testLabUpdateMapper.updateFromDto(labDTO, lab);
        return testLabMapper.toDto(testLabRepository.save(lab));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        testLabRepository.delete(getEntityById(id));
    }
} 