package com.fst.elearning.repository;

import com.fst.elearning.entity.Question;
import com.fst.elearning.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository
        extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);
    int countByQuiz(Quiz quiz);
}