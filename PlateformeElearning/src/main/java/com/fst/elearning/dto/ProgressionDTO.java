package com.fst.elearning.dto;

// DTO pour représenter la progression d’un utilisateur dans un cours
public class ProgressionDTO {

    private Long coursId;
    private String titre;
    private Double progression;

    // Constructeur vide 
    public ProgressionDTO() {
    }

    
    public ProgressionDTO(Long coursId, String titre, Double progression) {
        this.coursId = coursId;
        this.titre = titre;
        this.progression = progression;
    }

    // Getters & Setters
    public Long getCoursId() {
        return coursId;
    }

    public void setCoursId(Long coursId) {
        this.coursId = coursId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Double getProgression() {
        return progression;
    }

    public void setProgression(Double progression) {
        this.progression = progression;
    }
}