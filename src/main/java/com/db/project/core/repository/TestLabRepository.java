package com.db.project.core.repository;

import com.db.project.core.model.TestLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestLabRepository extends JpaRepository<TestLab, Integer> {
} 