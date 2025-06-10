package com.db.project.core.service.impl;

import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.ProductionProcess;
import com.db.project.core.repository.ProductionProcessRepository;
import com.db.project.core.service.ProductionProcessService;
import com.db.project.core.service.ProductService;
import com.db.project.core.service.BrigadeService;
import com.db.project.core.service.AssemblyJobService;
import com.db.project.api.dto.product.ProductShortDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessCreateDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessDTO;
import com.db.project.api.dto.productionprocess.ProductionProcessUpdateDTO;
import com.db.project.api.mapper.productionprocess.ProductionProcessMapper;
import com.db.project.api.mapper.productionprocess.ProductionProcessStatisticsMapper;
import com.db.project.api.mapper.productionprocess.ProductionProcessUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductionProcessServiceImpl implements ProductionProcessService {

    private final ProductionProcessRepository productionProcessRepository;
    private final ProductionProcessMapper productionProcessMapper;
    private final ProductionProcessUpdateMapper productionProcessUpdateMapper;
    private final ProductionProcessStatisticsMapper productionProcessStatisticsMapper;
    private final ProductService productService;
    private final BrigadeService brigadeService;
    private final AssemblyJobService assemblyJobService;

    @Override
    @Transactional
    public ProductionProcessDTO create(ProductionProcessCreateDTO processDTO) {
        ProductionProcess process = ProductionProcess.builder()
                .product(productService.getEntityById(processDTO.getProductId()))
                .brigade(brigadeService.getEntityById(processDTO.getBrigadeId()))
                .job(assemblyJobService.getEntityById(processDTO.getJobId()))
                .startDate(processDTO.getStartDate())
                .endDate(processDTO.getEndDate())
                .build();
        
        return productionProcessMapper.toDto(productionProcessRepository.save(process));
    }

    @Override
    public List<ProductionProcessDTO> getAll() {
        return productionProcessMapper.toDto(productionProcessRepository.findAll());
    }

    @Override
    public ProductionProcessDTO getById(Integer id) {
        return productionProcessMapper.toDto(getEntityById(id));
    }

    @Override
    public ProductionProcess getEntityById(Integer id) {
        return productionProcessRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Production process not found with id: " + id, ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public ProductionProcessDTO update(Integer id, ProductionProcessUpdateDTO processDTO) {
        ProductionProcess process = getEntityById(id);
        productionProcessUpdateMapper.updateFromDto(processDTO, process);
        return productionProcessMapper.toDto(productionProcessRepository.save(process));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productionProcessRepository.delete(getEntityById(id));
    }

    @Override
    public List<ProductionProcessDTO> getByProduct(Integer productId) {
        return productionProcessMapper.toDto(productionProcessRepository.findByProductIdWithDetails(productId));
    }

    @Override
    public List<ProductShortDTO> getCurrentProductsInProduction(Integer workshopId, Integer sectionId, Integer categoryId) {
        return productionProcessStatisticsMapper.toProductShortDto(
            productionProcessRepository.findCurrentProductsInProduction(workshopId, sectionId, categoryId)
        );
    }

    @Override
    public List<ProductShortDTO> getProductsInProduction(Integer workshopId, Integer sectionId, Integer categoryId) {
        return productionProcessStatisticsMapper.toProductShortDto(
            productionProcessRepository.findProductsInProduction(workshopId, sectionId, categoryId)
        );
    }
} 