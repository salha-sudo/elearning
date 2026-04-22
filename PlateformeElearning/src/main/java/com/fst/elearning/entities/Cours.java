package com.fst.elearning.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data // Lombok : génère getters, setters
public class Cours {

    @Id @GeneratedValue
    private Long id;

    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String categorie;

    @Enumerated(EnumType.STRING) // Stocke l'enum sous forme de texte
    private Niveau niveau;

    private String imageUrl;

    private boolean actif = true;

    @ManyToOne  // Plusieurs cours peuvent être créés par un seul formateur
    private Utilisateur formateur; 

    private LocalDateTime dateCreation = LocalDateTime.now();

	public void setId(Long id2) {
		
		
	}
	@OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
	private List<Module> modules;

   
}