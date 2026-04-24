package com.fst.elearning.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ProgressionLecon {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Utilisateur apprenant;

    @ManyToOne
    private Lecon lecon;

    private boolean completee = false;

    private LocalDateTime dateCompletion;
}
