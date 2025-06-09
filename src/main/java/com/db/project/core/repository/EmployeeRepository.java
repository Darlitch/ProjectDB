package com.db.project.core.repository;

import com.db.project.core.model.Employee;
import com.db.project.core.model.enums.EmployeeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    @Query("""
            SELECT DISTINCT e FROM Employee e
            LEFT JOIN e.directedWorkshops dw
            LEFT JOIN e.headedSections hs
            LEFT JOIN e.masterSections ms
            LEFT JOIN e.workerBrigades wb
            LEFT JOIN ms.section s
            LEFT JOIN wb.brigade b
            LEFT JOIN b.section bs
            WHERE (:workshopId IS NULL 
                OR dw.id = :workshopId 
                OR hs.workshop.id = :workshopId
                OR s.workshop.id = :workshopId
                OR bs.workshop.id = :workshopId)
            AND (:category IS NULL OR e.position.category = :category)
            ORDER BY e.name
            """)
    List<Employee> findByWorkshopAndCategory(
            @Param("workshopId") Integer workshopId,
            @Param("category") EmployeeCategory category);
} 