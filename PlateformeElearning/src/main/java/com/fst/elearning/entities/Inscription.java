package com.fst.elearning.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur apprenant;

    @ManyToOne
    private Cours cours;

    private LocalDate dateInscription;

    @Enumerated(EnumType.STRING)
    private StatutInscription statut;

   
    public Long getId() { return id; }

    public Utilisateur getApprenant() { return apprenant; }

    public Cours getCours() { return cours; }

    public LocalDate getDateInscription() { return dateInscription; }

    public StatutInscription getStatut() { return statut; }

    
    public void setId(Long id) { this.id = id; }

    public void setApprenant(Utilisateur apprenant) {
        this.apprenant = apprenant;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setStatut(StatutInscription statut) {
        this.statut = statut;
    }
}