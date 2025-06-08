package com.db.project.core.repository;

import com.db.project.core.model.TestEquipment;
import com.db.project.core.model.TestEquipmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestEquipmentRepository extends JpaRepository<TestEquipment, TestEquipmentId> {
//    List<TestEquipment> findAllByTestId(Integer testId);
//    List<TestEquipment> findAllByEquipmentId(Integer equipmentId);
} 