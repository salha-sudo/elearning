package com.fst.elearning.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Inscription {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne  // Plusieurs inscriptions pour un même apprenant
    private Utilisateur apprenant;

    @ManyToOne  // Plusieurs apprenants peuvent s'inscrire à un cours
    private Cours cours;

    private LocalDate dateInscription = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private StatutInscription statut;
}
