package com.db.project.core.repository;

import com.db.project.core.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    
    @Query("""
            SELECT s FROM Section s
            LEFT JOIN FETCH s.head h
            LEFT JOIN FETCH s.workshop w
            WHERE (:workshopId IS NULL OR s.workshop.id = :workshopId)
            ORDER BY s.name
            """)
    List<Section> findByWorkshopWithHead(@Param("workshopId") Integer workshopId);
} 