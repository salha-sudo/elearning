package com.fst.elearning.repository;

import com.fst.elearning.entities.Lecon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeconRepository extends JpaRepository<Lecon, Long> { // Repository pour l'entité Lecon
} 