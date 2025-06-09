package com.db.project.core.repository;

import com.db.project.core.model.SectionMaster;
import com.db.project.core.model.SectionMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionMasterRepository extends JpaRepository<SectionMaster, SectionMasterId> {
//    List<SectionMaster> findAllBySectionId(Integer sectionId);
//    List<SectionMaster> findAllByEmployeeId(Integer employeeId);

    @Query("""
            SELECT sm FROM SectionMaster sm
            LEFT JOIN FETCH sm.employee e
            LEFT JOIN FETCH sm.section s
            LEFT JOIN FETCH s.workshop w
            WHERE (:sectionId IS NULL OR s.id = :sectionId)
            AND (:workshopId IS NULL OR s.workshop.id = :workshopId)
            ORDER BY e.name
            """)
    List<SectionMaster> findByWorkshopOrSection(
            @Param("workshopId") Integer workshopId,
            @Param("sectionId") Integer sectionId);
} 