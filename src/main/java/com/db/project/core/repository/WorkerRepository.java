package com.db.project.core.repository;

import com.db.project.core.model.Worker;
import com.db.project.core.model.WorkerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, WorkerId> {
//    List<Worker> findAllByBrigadeId(Integer brigadeId);
//    List<Worker> findAllByEmployeeId(Integer employeeId);
} 