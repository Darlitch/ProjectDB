package com.db.project.core.repository;

import com.db.project.core.model.Tester;
import com.db.project.core.model.TesterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TesterRepository extends JpaRepository<Tester, TesterId> {
//    List<Tester> findAllByLabId(Integer labId);
//    List<Tester> findAllByEmployeeId(Integer employeeId);
} 