package com.fst.elearning.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @OneToOne
    @JoinColumn(name = "module_id")
    private CourseModule module;    
    @OneToMany(mappedBy = "quiz",
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<Question> questions;
}