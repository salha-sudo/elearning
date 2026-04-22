package com.fst.elearning.repository;

import com.fst.elearning.entities.Inscription;
import com.fst.elearning.dto.ProgressionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {  // Repository pour l'entité Inscription

   
	// Requête personnalisée JPQL pour calculer la progression d’un utilisateur
	@Query("SELECT new com.fst.elearning.dto.ProgressionDTO(c.titre, COUNT(p)*100.0 / COUNT(l)) " +
           "FROM Inscription i " +
           "JOIN i.cours c " + // relie inscription → cours
           "JOIN Module m ON m.cours = c " +  // récupère les modules du cours
           "JOIN Lecon l ON l.module = m " +   // récupère les leçons du module
           "LEFT JOIN ProgressionLecon p ON p.lecon = l AND p.apprenant = i.apprenant " +  // récupère les leçons complétées par l'utilisateur
           "WHERE i.apprenant.id = :userId " +  // filtre par utilisateur
           "GROUP BY c.titre") // groupement par cours
    List<ProgressionDTO> getProgression(Long userId);
}