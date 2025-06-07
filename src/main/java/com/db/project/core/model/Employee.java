package com.db.project.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Column(name = "hire_date", nullable = false)
    @Builder.Default
    private LocalDate hireDate = LocalDate.now();

    @OneToMany(mappedBy = "director")
    @Builder.Default
    private List<Workshop> directedWorkshops = new ArrayList<>();

    @OneToMany(mappedBy = "head")
    @Builder.Default
    private List<Section> headedSections = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @Builder.Default
    private List<SectionMaster> masterSections = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @Builder.Default
    private List<Worker> workerBrigades = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @Builder.Default
    private List<Tester> testAssignments = new ArrayList<>();
} 