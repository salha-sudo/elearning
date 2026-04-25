package com.fst.elearning.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Lecon {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(columnDefinition = "TEXT")     // Contenu détaillé de la leçon
    private String contenu;

    private int ordre;

    private int dureeMin;

    @ManyToOne // Plusieurs leçons appartiennent à un seul module
    private Module module;
}
