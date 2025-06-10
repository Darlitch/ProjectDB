package com.db.project.core.repository;

import com.db.project.core.model.TestLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestLabRepository extends JpaRepository<TestLab, Integer> {
    @Query("SELECT DISTINCT t.lab FROM Test t WHERE t.product.id = :productId")
    List<TestLab> findAllByProductId(@Param("productId") Integer productId);
} 