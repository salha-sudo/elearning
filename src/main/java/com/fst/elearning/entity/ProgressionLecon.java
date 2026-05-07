package com.fst.elearning.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progression_lecons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressionLecon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Utilisateur apprenant;

    @ManyToOne
    @JoinColumn(name = "lecon_id")
    private Lecon lecon;

    private boolean completee = false;

    private LocalDateTime dateCompletion;
}