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
public class Module {

    @Id @GeneratedValue
    private Long id;

    private String titre;

    private String description;

    private int ordre;

    @ManyToOne // Plusieurs modules appartiennent à un seul cours
    private Cours cours;
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Lecon> lecons;

   
}
