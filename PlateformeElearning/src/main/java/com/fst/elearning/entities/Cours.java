package com.fst.elearning.entities;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Cours {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String categorie;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    private String imageUrl;
    private boolean actif = true;
    @ManyToOne
    private Utilisateur formateur;
    private LocalDateTime dateCreation = LocalDateTime.now();
    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
    private List<Module> modules;

    // Getters
    public Long getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public String getCategorie() { return categorie; }
    public Niveau getNiveau() { return niveau; }
    public String getImageUrl() { return imageUrl; }
    public boolean isActif() { return actif; }
    public Utilisateur getFormateur() { return formateur; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public List<Module> getModules() { return modules; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setDescription(String description) { this.description = description; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setNiveau(Niveau niveau) { this.niveau = niveau; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setActif(boolean actif) { this.actif = actif; }
    public void setFormateur(Utilisateur formateur) { this.formateur = formateur; }
    public void setModules(List<Module> modules) { this.modules = modules; }
}