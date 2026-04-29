package com.fst.elearning.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Module {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String description;

    private int ordre;

    @ManyToOne
    private Cours cours;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Lecon> lecons;
}