package com.db.project.core.repository;

import com.db.project.core.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    
    @Query("""
            SELECT DISTINCT t FROM Test t
            JOIN FETCH t.product p
            JOIN FETCH p.type pt
            JOIN FETCH pt.category pc
            JOIN FETCH p.workshop w
            WHERE (:labId IS NULL OR t.lab.id = :labId)
            AND (:categoryId IS NULL OR pc.id = :categoryId)
            AND (CAST(:startDate AS date) IS NULL OR t.startDate >= :startDate)
            AND (CAST(:endDate AS date) IS NULL OR t.startDate <= :endDate)
            ORDER BY pc.name, pt.name, p.serialNum
            """)
    List<Test> findProductsByLabAndPeriod(
            @Param("labId") Integer labId,
            @Param("categoryId") Integer categoryId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
} 