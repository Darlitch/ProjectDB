package com.db.project.core.repository;

import com.db.project.core.model.Tester;
import com.db.project.core.model.TesterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TesterRepository extends JpaRepository<Tester, TesterId> {
//    List<Tester> findAllByLabId(Integer labId);
//    List<Tester> findAllByEmployeeId(Integer employeeId);

    @Query("""
            SELECT DISTINCT t FROM Tester t
            JOIN FETCH t.employee e
            JOIN FETCH e.position p
            JOIN t.test test
            JOIN test.product prod
            JOIN prod.type pt
            JOIN pt.category pc
            WHERE (:labId IS NULL OR test.lab.id = :labId)
            AND (:productId IS NULL OR prod.id = :productId)
            AND (:categoryId IS NULL OR pc.id = :categoryId)
            AND (CAST(:startDate AS date) IS NULL OR test.startDate >= :startDate)
            AND (CAST(:endDate AS date) IS NULL OR test.startDate <= :endDate)
            ORDER BY e.name
            """)
    List<Tester> findTestersByParameters(
            @Param("labId") Integer labId,
            @Param("productId") Integer productId,
            @Param("categoryId") Integer categoryId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
} 