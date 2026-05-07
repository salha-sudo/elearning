package com.fst.elearning.entity;

import com.fst.elearning.enums.Niveau;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cours")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String categorie;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    private String imageUrl;

    private boolean actif = true;

    private LocalDateTime dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formateur_id")
    private Utilisateur formateur;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("ordre ASC")
    private List<CourseModule> modules;

    @OneToMany(mappedBy = "cours", fetch = FetchType.LAZY)
    private List<Inscription> inscriptions;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
    }
}
