package com.fst.elearning.repository;

import com.fst.elearning.entity.Cours;
import com.fst.elearning.entity.Utilisateur;
import com.fst.elearning.enums.Niveau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoursRepository extends JpaRepository<Cours, Long> {

    @Query("SELECT c FROM Cours c WHERE c.actif = true " +
           "AND (:titre IS NULL OR LOWER(c.titre) LIKE LOWER(CONCAT('%', :titre, '%'))) " +
           "AND (:categorie IS NULL OR c.categorie = :categorie) " +
           "AND (:niveau IS NULL OR c.niveau = :niveau)")
    Page<Cours> findCatalogueFiltre(
        @Param("titre") String titre,
        @Param("categorie") String categorie,
        @Param("niveau") Niveau niveau,
        Pageable pageable
    );

    Page<Cours> findByFormateur(Utilisateur formateur, Pageable pageable);
    long countByActifTrue();
}
