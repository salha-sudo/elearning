package com.fst.elearning.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "modules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    private String description;

    private int ordre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @OneToMany(mappedBy = "module",
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    @OrderBy("ordre ASC")
    private List<Lecon> lecons;

    @OneToOne(mappedBy = "module",
              cascade = CascadeType.ALL)
    private Quiz quiz;
}