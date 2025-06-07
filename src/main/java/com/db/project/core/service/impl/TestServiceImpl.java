package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Test;
import com.db.project.core.repository.TestRepository;
import com.db.project.core.service.TestService;
import com.db.project.core.service.ProductService;
import com.db.project.core.service.TestLabService;
import com.db.project.api.dto.test.TestCreateDTO;
import com.db.project.api.dto.test.TestDTO;
import com.db.project.api.dto.test.TestUpdateDTO;
import com.db.project.api.mapper.test.TestMapper;
import com.db.project.api.mapper.test.TestUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final TestUpdateMapper testUpdateMapper;
    private final ProductService productService;
    private final TestLabService testLabService;

    @Override
    @Transactional
    public TestDTO create(TestCreateDTO testDTO) {
        Test test = Test.builder()
                .product(productService.getEntityById(testDTO.getProductId()))
                .lab(testLabService.getEntityById(testDTO.getLabId()))
                .result(testDTO.getResult())
                .startDate(testDTO.getStartDate())
                .endDate(testDTO.getEndDate())
                .build();
        
        return testMapper.toDto(testRepository.save(test));
    }

    @Override
    public List<TestDTO> getAll() {
        return testMapper.toDto(testRepository.findAll());
    }

    @Override
    public TestDTO getById(Integer id) {
        return testMapper.toDto(getEntityById(id));
    }

    @Override
    public Test getEntityById(Integer id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Test not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public TestDTO update(Integer id, TestUpdateDTO testDTO) {
        Test test = getEntityById(id);
        testUpdateMapper.updateFromDto(testDTO, test);
        return testMapper.toDto(testRepository.save(test));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        testRepository.delete(getEntityById(id));
    }
} 