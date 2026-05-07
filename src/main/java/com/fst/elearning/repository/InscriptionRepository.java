package com.fst.elearning.repository;

import com.fst.elearning.entity.Cours;
import com.fst.elearning.entity.Inscription;
import com.fst.elearning.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InscriptionRepository 
        extends JpaRepository<Inscription, Long> {
	 long countByCours(Cours cours);

    // Trouver les inscriptions d'un apprenant
    List<Inscription> findByApprenant(Utilisateur apprenant);

    // Trouver les inscriptions d'un cours (pour formateur)
    List<Inscription> findByCours(Cours cours);

    // Vérifier si un apprenant est inscrit à un cours
    boolean existsByApprenantAndCours(
        Utilisateur apprenant, Cours cours);
   
}
