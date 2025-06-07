package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.ProductCategory;
import com.db.project.core.repository.ProductCategoryRepository;
import com.db.project.core.service.ProductCategoryService;
import com.db.project.api.dto.productcategory.ProductCategoryCreateDTO;
import com.db.project.api.dto.productcategory.ProductCategoryDTO;
import com.db.project.api.dto.productcategory.ProductCategoryUpdateDTO;
import com.db.project.api.mapper.productcategory.ProductCategoryMapper;
import com.db.project.api.mapper.productcategory.ProductCategoryUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryUpdateMapper productCategoryUpdateMapper;

    @Override
    @Transactional
    public ProductCategoryDTO create(ProductCategoryCreateDTO categoryDTO) {
        ProductCategory category = ProductCategory.builder()
                .name(categoryDTO.getName())
                .build();
        
        return productCategoryMapper.toDto(productCategoryRepository.save(category));
    }

    @Override
    public List<ProductCategoryDTO> getAll() {
        return productCategoryMapper.toDto(productCategoryRepository.findAll());
    }

    @Override
    public ProductCategoryDTO getById(Integer id) {
        return productCategoryMapper.toDto(getEntityById(id));
    }

    @Override
    public ProductCategory getEntityById(Integer id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Product category not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public ProductCategoryDTO update(Integer id, ProductCategoryUpdateDTO categoryDTO) {
        ProductCategory category = getEntityById(id);
        productCategoryUpdateMapper.updateFromDto(categoryDTO, category);
        return productCategoryMapper.toDto(productCategoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productCategoryRepository.delete(getEntityById(id));
    }
}