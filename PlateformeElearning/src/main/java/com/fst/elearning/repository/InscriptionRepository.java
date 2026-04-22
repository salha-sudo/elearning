package com.fst.elearning.repository;

import com.fst.elearning.entities.Inscription;
import com.fst.elearning.dto.ProgressionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    @Query("SELECT new com.fst.elearning.dto.ProgressionDTO(c.titre, COUNT(p)*100.0 / COUNT(l)) " +
           "FROM Inscription i " +
           "JOIN i.cours c " +
           "JOIN Module m ON m.cours = c " +
           "JOIN Lecon l ON l.module = m " +
           "LEFT JOIN ProgressionLecon p ON p.lecon = l AND p.apprenant = i.apprenant " +
           "WHERE i.apprenant.id = :userId " +
           "GROUP BY c.titre")
    List<ProgressionDTO> getProgression(Long userId);
}