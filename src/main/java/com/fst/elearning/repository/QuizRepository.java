package com.fst.elearning.repository;

import com.fst.elearning.entity.Quiz;
import com.fst.elearning.entity.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QuizRepository
        extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByModule(CourseModule module);
}