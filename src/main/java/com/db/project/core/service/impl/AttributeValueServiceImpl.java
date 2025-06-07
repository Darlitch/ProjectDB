package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.AttributeValue;
import com.db.project.core.repository.AttributeValueRepository;
import com.db.project.core.service.AttributeValueService;
import com.db.project.core.service.ProductService;
import com.db.project.core.service.ProductAttributeService;
import com.db.project.api.dto.attributevalue.AttributeValueCreateDTO;
import com.db.project.api.dto.attributevalue.AttributeValueDTO;
import com.db.project.api.dto.attributevalue.AttributeValueUpdateDTO;
import com.db.project.api.mapper.attributevalue.AttributeValueMapper;
import com.db.project.api.mapper.attributevalue.AttributeValueUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttributeValueServiceImpl implements AttributeValueService {

    private final AttributeValueRepository attributeValueRepository;
    private final AttributeValueMapper attributeValueMapper;
    private final AttributeValueUpdateMapper attributeValueUpdateMapper;
    private final ProductService productService;
    private final ProductAttributeService productAttributeService;

    @Override
    @Transactional
    public AttributeValueDTO create(AttributeValueCreateDTO valueDTO) {
        AttributeValue value = AttributeValue.builder()
                .product(productService.getEntityById(valueDTO.getProductId()))
                .attribute(productAttributeService.getEntityById(valueDTO.getAttributeId()))
                .value(valueDTO.getValue())
                .build();
        
        return attributeValueMapper.toDto(attributeValueRepository.save(value));
    }

    @Override
    public List<AttributeValueDTO> getAll() {
        return attributeValueMapper.toDto(attributeValueRepository.findAll());
    }

    @Override
    public AttributeValueDTO getById(Integer id) {
        return attributeValueMapper.toDto(getEntityById(id));
    }

    @Override
    public AttributeValue getEntityById(Integer id) {
        return attributeValueRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Attribute value not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public AttributeValueDTO update(Integer id, AttributeValueUpdateDTO valueDTO) {
        AttributeValue value = getEntityById(id);
        attributeValueUpdateMapper.updateFromDto(valueDTO, value);
        return attributeValueMapper.toDto(attributeValueRepository.save(value));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        attributeValueRepository.delete(getEntityById(id));
    }
} 