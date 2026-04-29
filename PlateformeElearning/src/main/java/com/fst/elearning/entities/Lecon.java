package com.fst.elearning.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lecon {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private int ordre;

    private int dureeMin;

    @ManyToOne
    private Module module;
}