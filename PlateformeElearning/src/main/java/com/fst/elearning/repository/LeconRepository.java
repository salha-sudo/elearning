package com.fst.elearning.repository;

import com.fst.elearning.entities.Lecon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeconRepository extends JpaRepository<Lecon, Long> {
    List<Lecon> findByModuleId(Long moduleId);
}