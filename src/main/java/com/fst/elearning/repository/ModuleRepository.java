package com.fst.elearning.repository;

import com.fst.elearning.entity.CourseModule;
import com.fst.elearning.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModuleRepository
        extends JpaRepository<CourseModule, Long> {

    List<CourseModule> findByCoursOrderByOrdreAsc(Cours cours);
    int countByCours(Cours cours);
}
