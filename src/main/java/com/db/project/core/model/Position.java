package com.db.project.core.model;

import com.db.project.core.model.enums.EmployeeCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private EmployeeCategory category;

    @OneToMany(mappedBy = "position")
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();
} 