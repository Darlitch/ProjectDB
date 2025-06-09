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
} 