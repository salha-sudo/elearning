package com.fst.elearning.dto;

public class ProgressionDTO {

    private String cours;
    private double progression;

    public ProgressionDTO(String cours, double progression) {
        this.cours = cours;
        this.progression = progression;
    }

    public String getCours() { return cours; }
    public double getProgression() { return progression; }
}