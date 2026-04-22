package com.fst.elearning.dto;

public class CourseCardDTO { // DTO = Data Transfer Object Sert à envoyer uniquement les données nécessaires au frontend 


	    private Long id;
	    private String titre;
	    private String imageUrl;
	    private String categorie;

	    // Constructeur vide 
	    public CourseCardDTO() {
	    }

	    // Constructeur complet
	    public CourseCardDTO(Long id, String titre, String imageUrl, String categorie) {
	        this.id = id;
	        this.titre = titre;
	        this.imageUrl = imageUrl;
	        this.categorie = categorie;
	    }

	    // Getters & Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTitre() {
	        return titre;
	    }

	    public void setTitre(String titre) {
	        this.titre = titre;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public String getCategorie() {
	        return categorie;
	    }

	    public void setCategorie(String categorie) {
	        this.categorie = categorie;
	    }
	}