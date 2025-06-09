package com.db.project.core.repository;

import com.db.project.core.model.Brigade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrigadeRepository extends JpaRepository<Brigade, Integer> {
    
    @Query("""
            SELECT DISTINCT b FROM Brigade b
            LEFT JOIN FETCH b.workers w
            LEFT JOIN FETCH w.employee e
            LEFT JOIN FETCH b.section s
            WHERE (:sectionId IS NULL OR s.id = :sectionId)
            AND (:workshopId IS NULL OR s.workshop.id = :workshopId)
            ORDER BY b.name, e.name
            """)
    List<Brigade> findByWorkshopOrSectionWithWorkers(
            @Param("workshopId") Integer workshopId,
            @Param("sectionId") Integer sectionId);
} 