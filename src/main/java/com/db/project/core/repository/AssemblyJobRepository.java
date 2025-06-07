package com.db.project.core.repository;

import com.db.project.core.model.AssemblyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssemblyJobRepository extends JpaRepository<AssemblyJob, Integer> {
} 