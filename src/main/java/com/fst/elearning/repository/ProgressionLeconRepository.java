package com.fst.elearning.repository;

import com.fst.elearning.entity.ProgressionLecon;
import com.fst.elearning.entity.Utilisateur;
import com.fst.elearning.entity.Lecon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ProgressionLeconRepository extends JpaRepository<ProgressionLecon, Long> {
    Optional<ProgressionLecon> findByApprenantAndLecon(Utilisateur apprenant, Lecon lecon);

    @Query("SELECT COUNT(p) FROM ProgressionLecon p " +
           "WHERE p.apprenant = :apprenant " +
           "AND p.lecon.module.cours.id = :coursId " +
           "AND p.completee = true")
    int countLeconsCompleteesByCoursId(
        @Param("apprenant") Utilisateur apprenant,
        @Param("coursId") Long coursId
    );
}
