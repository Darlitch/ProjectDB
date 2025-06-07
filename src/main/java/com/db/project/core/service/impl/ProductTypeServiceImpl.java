package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.ProductType;
import com.db.project.core.repository.ProductTypeRepository;
import com.db.project.core.service.ProductTypeService;
import com.db.project.core.service.ProductCategoryService;
import com.db.project.api.dto.producttype.ProductTypeCreateDTO;
import com.db.project.api.dto.producttype.ProductTypeDTO;
import com.db.project.api.dto.producttype.ProductTypeUpdateDTO;
import com.db.project.api.mapper.producttype.ProductTypeMapper;
import com.db.project.api.mapper.producttype.ProductTypeUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeUpdateMapper productTypeUpdateMapper;
    private final ProductCategoryService productCategoryService;

    @Override
    @Transactional
    public ProductTypeDTO create(ProductTypeCreateDTO typeDTO) {
        ProductType type = ProductType.builder()
                .name(typeDTO.getName())
                .category(productCategoryService.getEntityById(typeDTO.getCategoryId()))
                .build();
        
        return productTypeMapper.toDto(productTypeRepository.save(type));
    }

    @Override
    public List<ProductTypeDTO> getAll() {
        return productTypeMapper.toDto(productTypeRepository.findAll());
    }

    @Override
    public ProductTypeDTO getById(Integer id) {
        return productTypeMapper.toDto(getEntityById(id));
    }

    @Override
    public ProductType getEntityById(Integer id) {
        return productTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Product type not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public ProductTypeDTO update(Integer id, ProductTypeUpdateDTO typeDTO) {
        ProductType type = getEntityById(id);
        productTypeUpdateMapper.updateFromDto(typeDTO, type);
        return productTypeMapper.toDto(productTypeRepository.save(type));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productTypeRepository.delete(getEntityById(id));
    }
} 