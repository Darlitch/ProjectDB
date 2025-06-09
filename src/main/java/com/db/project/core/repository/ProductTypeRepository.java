package com.db.project.core.repository;

import com.db.project.core.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    
    @Query("SELECT DISTINCT pt FROM ProductType pt " +
           "WHERE pt.category.id = :categoryId")
    List<ProductType> findByCategory(@Param("categoryId") Integer categoryId);

    @Query("SELECT DISTINCT pt FROM ProductType pt " +
           "JOIN pt.products p " +
           "WHERE p.workshop.id = :workshopId")
    List<ProductType> findByWorkshop(@Param("workshopId") Integer workshopId);

    @Query("SELECT DISTINCT pt FROM ProductType pt " +
           "JOIN pt.products p " +
           "WHERE p.workshop.id = :workshopId " +
           "AND pt.category.id = :categoryId")
    List<ProductType> findByWorkshopAndCategory(
            @Param("workshopId") Integer workshopId,
            @Param("categoryId") Integer categoryId);
} 