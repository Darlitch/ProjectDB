package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Product;
import com.db.project.core.repository.ProductRepository;
import com.db.project.core.service.ProductService;
import com.db.project.core.service.ProductTypeService;
import com.db.project.core.service.WorkshopService;
import com.db.project.api.dto.product.ProductCreateDTO;
import com.db.project.api.dto.product.ProductDTO;
import com.db.project.api.dto.product.ProductUpdateDTO;
import com.db.project.api.mapper.product.ProductMapper;
import com.db.project.api.mapper.product.ProductUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductUpdateMapper productUpdateMapper;
    private final ProductTypeService productTypeService;
    private final WorkshopService workshopService;

    @Override
    @Transactional
    public ProductDTO create(ProductCreateDTO productDTO) {
        Product product = Product.builder()
                .serialNum(productDTO.getSerialNum())
                .type(productTypeService.getEntityById(productDTO.getTypeId()))
                .workshop(workshopService.getEntityById(productDTO.getWorkshopId()))
                .build();
        
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDTO> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    public ProductDTO getById(Integer id) {
        return productMapper.toDto(getEntityById(id));
    }

    @Override
    public Product getEntityById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Product not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public ProductDTO update(Integer id, ProductUpdateDTO productDTO) {
        Product product = getEntityById(id);
        productUpdateMapper.updateFromDto(productDTO, product);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productRepository.delete(getEntityById(id));
    }
} 