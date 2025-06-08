package com.db.project.core.repository;

import com.db.project.core.model.SectionMaster;
import com.db.project.core.model.SectionMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionMasterRepository extends JpaRepository<SectionMaster, SectionMasterId> {
//    List<SectionMaster> findAllBySectionId(Integer sectionId);
//    List<SectionMaster> findAllByEmployeeId(Integer employeeId);
} 