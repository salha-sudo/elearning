package com.fst.elearning.entity;

import com.fst.elearning.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean actif = true;

    @OneToMany(mappedBy = "formateur", fetch = FetchType.LAZY)
    private List<Cours> cours;

    @OneToMany(mappedBy = "apprenant", fetch = FetchType.LAZY)
    private List<Inscription> inscriptions;
}
