package com.db.project.core.repository;

import com.db.project.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query("SELECT DISTINCT p FROM Product p " +
           "JOIN p.productionProcesses pp " +
           "WHERE p.workshop.id = :workshopId " +
           "AND (:categoryId IS NULL OR p.type.category.id = :categoryId) " +
           "AND pp.startDate <= :endDate " +
           "AND COALESCE(pp.endDate, CURRENT_DATE) >= :startDate")
    List<Product> findByWorkshopAndCategoryAndPeriod(
            @Param("workshopId") Integer workshopId,
            @Param("categoryId") Integer categoryId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT DISTINCT p FROM Product p " +
           "JOIN p.productionProcesses pp " +
           "JOIN pp.brigade b " +
           "WHERE b.section.id = :sectionId " +
           "AND (:categoryId IS NULL OR p.type.category.id = :categoryId) " +
           "AND pp.startDate <= :endDate " +
           "AND COALESCE(pp.endDate, CURRENT_DATE) >= :startDate")
    List<Product> findBySectionAndCategoryAndPeriod(
            @Param("sectionId") Integer sectionId,
            @Param("categoryId") Integer categoryId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT DISTINCT p FROM Product p " +
           "JOIN p.productionProcesses pp " +
           "WHERE (:categoryId IS NULL OR p.type.category.id = :categoryId) " +
           "AND pp.startDate <= :endDate " +
           "AND COALESCE(pp.endDate, CURRENT_DATE) >= :startDate")
    List<Product> findByCategoryAndPeriod(
            @Param("categoryId") Integer categoryId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
} 