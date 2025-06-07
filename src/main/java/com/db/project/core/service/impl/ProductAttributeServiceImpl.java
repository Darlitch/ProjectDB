package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.ProductAttribute;
import com.db.project.core.repository.ProductAttributeRepository;
import com.db.project.core.service.ProductAttributeService;
import com.db.project.core.service.ProductCategoryService;
import com.db.project.api.dto.productattribute.ProductAttributeCreateDTO;
import com.db.project.api.dto.productattribute.ProductAttributeDTO;
import com.db.project.api.dto.productattribute.ProductAttributeUpdateDTO;
import com.db.project.api.mapper.productattribute.ProductAttributeMapper;
import com.db.project.api.mapper.productattribute.ProductAttributeUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;
    private final ProductAttributeMapper productAttributeMapper;
    private final ProductAttributeUpdateMapper productAttributeUpdateMapper;
    private final ProductCategoryService productCategoryService;

    @Override
    @Transactional
    public ProductAttributeDTO create(ProductAttributeCreateDTO attributeDTO) {
        ProductAttribute attribute = ProductAttribute.builder()
                .name(attributeDTO.getName())
                .category(productCategoryService.getEntityById(attributeDTO.getCategoryId()))
                .build();
        
        return productAttributeMapper.toDto(productAttributeRepository.save(attribute));
    }

    @Override
    public List<ProductAttributeDTO> getAll() {
        return productAttributeMapper.toDto(productAttributeRepository.findAll());
    }

    @Override
    public ProductAttributeDTO getById(Integer id) {
        return productAttributeMapper.toDto(getEntityById(id));
    }

    @Override
    public ProductAttribute getEntityById(Integer id) {
        return productAttributeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Product attribute not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public ProductAttributeDTO update(Integer id, ProductAttributeUpdateDTO attributeDTO) {
        ProductAttribute attribute = getEntityById(id);
        productAttributeUpdateMapper.updateFromDto(attributeDTO, attribute);
        return productAttributeMapper.toDto(productAttributeRepository.save(attribute));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productAttributeRepository.delete(getEntityById(id));
    }
} 