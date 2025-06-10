package com.db.project.core.repository;

import com.db.project.core.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Query("""
        SELECT DISTINCT e
        FROM Equipment e
        LEFT JOIN e.testUsages te
        JOIN te.test t
        WHERE
            (:productId IS NULL OR t.product.id = :productId)
            AND (:categoryId IS NULL OR t.product.type.category.id = :categoryId)
            AND (:labId IS NULL OR e.lab.id = :labId)
            AND (CAST(:startDate AS date) IS NULL OR t.startDate >= :startDate)
            AND (CAST(:endDate AS date) IS NULL OR t.startDate <= :endDate)
        ORDER BY e.name
    """)
    List<Equipment> findEquipmentByTestParameters(
            @Param("productId") Integer productId,
            @Param("categoryId") Integer categoryId,
            @Param("labId") Integer labId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
} 