package com.fst.elearning.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reponses_apprenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReponseApprenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Utilisateur apprenant;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private int score;
    private int totalQuestions;

    private LocalDateTime dateSoumission;

    @PrePersist
    public void prePersist() {
        this.dateSoumission = LocalDateTime.now();
    }
}
