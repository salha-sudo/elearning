package com.fst.elearning.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class ProgressionLecon {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur apprenant;

    @ManyToOne
    private Lecon lecon;

    private boolean completee = false;

    private LocalDateTime dateCompletion;

    // ===== GETTERS =====
    public Long getId() { return id; }

    public Utilisateur getApprenant() { return apprenant; }

    public Lecon getLecon() { return lecon; }

    public boolean isCompletee() { return completee; }

    public LocalDateTime getDateCompletion() { return dateCompletion; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }

    public void setApprenant(Utilisateur apprenant) { this.apprenant = apprenant; }

    public void setLecon(Lecon lecon) { this.lecon = lecon; }

    public void setCompletee(boolean completee) { this.completee = completee; }

    public void setDateCompletion(LocalDateTime dateCompletion) { this.dateCompletion = dateCompletion; }
}
