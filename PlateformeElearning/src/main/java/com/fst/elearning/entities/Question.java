package com.fst.elearning.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id @GeneratedValue
    private Long id;

    private String titre;

    private String choix1;
    private String choix2;
    private String choix3;
    private String choix4;

    private int bonneReponse; 

    @ManyToOne
    private Quiz quiz;
}