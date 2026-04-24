package com.fst.elearning.repository;

import com.fst.elearning.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCoursId(Long coursId);
}