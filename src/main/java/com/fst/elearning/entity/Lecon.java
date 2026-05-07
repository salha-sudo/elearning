package com.fst.elearning.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private int ordre;
    private int dureeMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private CourseModule module;    // ← CourseModule ici
}