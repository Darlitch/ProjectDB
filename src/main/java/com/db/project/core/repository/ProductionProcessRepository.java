package com.db.project.core.repository;

import com.db.project.core.model.ProductionProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionProcessRepository extends JpaRepository<ProductionProcess, Integer> {
    
    @Query("""
            SELECT pp FROM ProductionProcess pp
            LEFT JOIN FETCH pp.job j
            LEFT JOIN FETCH pp.brigade b
            LEFT JOIN FETCH b.section s
            WHERE pp.product.id = :productId
            ORDER BY pp.startDate
            """)
    List<ProductionProcess> findByProductIdWithDetails(@Param("productId") Integer productId);

    @Query("""
            SELECT DISTINCT pp FROM ProductionProcess pp
            JOIN FETCH pp.product p
            JOIN FETCH p.type pt
            JOIN FETCH pt.category pc
            JOIN FETCH p.workshop w
            JOIN pp.job j
            JOIN j.section s
            WHERE pp.endDate IS NULL
            AND (:workshopId IS NULL OR w.id = :workshopId)
            AND (:sectionId IS NULL OR s.id = :sectionId)
            AND (:categoryId IS NULL OR pc.id = :categoryId)
            ORDER BY pc.name, pt.name, p.serialNum
            """)
    List<ProductionProcess> findCurrentProductsInProduction(
            @Param("workshopId") Integer workshopId,
            @Param("sectionId") Integer sectionId,
            @Param("categoryId") Integer categoryId
    );

    @Query("""
            SELECT COUNT(DISTINCT pp.id) FROM ProductionProcess pp
            JOIN pp.product p
            JOIN p.type pt
            JOIN pt.category pc
            JOIN p.workshop w
            JOIN pp.job j
            JOIN j.section s
            WHERE pp.endDate IS NULL
            AND (:workshopId IS NULL OR w.id = :workshopId)
            AND (:sectionId IS NULL OR s.id = :sectionId)
            AND (:categoryId IS NULL OR pc.id = :categoryId)
            """)
    long countCurrentProduction(
            @Param("workshopId") Integer workshopId,
            @Param("sectionId") Integer sectionId,
            @Param("categoryId") Integer categoryId
    );
} 