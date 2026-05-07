package com.fst.elearning.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String enonce;

    private String choixa;
    private String choixb;
    private String choixc;
    private String choixd;

    private String bonneReponse; // "A", "B", "C" ou "D"

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
