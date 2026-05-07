package com.fst.elearning.repository;

import com.fst.elearning.entity.Lecon;
import com.fst.elearning.entity.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LeconRepository extends JpaRepository<Lecon, Long> {
    List<Lecon> findByModuleOrderByOrdreAsc(CourseModule module);
    int countByModule(CourseModule module);

    @Query("SELECT l FROM Lecon l WHERE l.module.cours.id = :coursId")
    List<Lecon> findAllByCoursId(@Param("coursId") Long coursId);
}
