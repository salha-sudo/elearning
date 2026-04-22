package com.fst.elearning.dto;

// DTO pour retourner le résultat d’un quiz
public class QuizResultDTO {

    private Long quizId;
    private int score;

    // Constructeur vide
    public QuizResultDTO() {
    }

    // Constructeur complet
    public QuizResultDTO(Long quizId, int score) {
        this.quizId = quizId;
        this.score = score;
    }

    // Getters & Setters
    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}