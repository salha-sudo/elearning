package com.fst.elearning.repository;

import com.fst.elearning.entities.Cours;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository pour gérer l'entité Cours
//JpaRepository fournit  les opérations CRUD

public interface CoursRepository extends JpaRepository<Cours, Long> {

    Page<Cours> findByTitreContaining(String keyword, Pageable pageable);
}