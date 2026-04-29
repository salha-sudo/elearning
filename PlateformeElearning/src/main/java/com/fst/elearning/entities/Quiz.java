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
public class Quiz {

    @Id @GeneratedValue
    private Long id;

    private String titre;

    @ManyToOne  // Chaque quiz est lié à un module
    private Module module;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL) // Un quiz contient plusieurs questions
    private List<Question> questions;
    
}
