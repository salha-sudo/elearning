package com.fst.elearning.entity;

import com.fst.elearning.enums.StatutInscription;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Utilisateur apprenant;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    private LocalDate dateInscription;

    @Enumerated(EnumType.STRING)
    private StatutInscription statut;

    @PrePersist
    public void prePersist() {
        this.dateInscription = LocalDate.now();
        if (this.statut == null) this.statut = StatutInscription.EN_COURS;
    }
}
