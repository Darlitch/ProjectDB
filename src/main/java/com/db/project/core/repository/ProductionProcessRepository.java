package com.db.project.core.repository;

import com.db.project.core.model.ProductionProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionProcessRepository extends JpaRepository<ProductionProcess, Integer> {
} 