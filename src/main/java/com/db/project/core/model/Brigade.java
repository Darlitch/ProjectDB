package com.db.project.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brigades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @OneToMany(mappedBy = "brigade", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Worker> workers = new ArrayList<>();

    @OneToMany(mappedBy = "brigade")
    @Builder.Default
    private List<ProductionProcess> productionProcesses = new ArrayList<>();
} 