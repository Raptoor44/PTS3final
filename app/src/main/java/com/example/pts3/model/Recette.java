package com.example.pts3.model;

public class Recette {


    private String nom;
    private String detaille;


    public Recette(String nom, String detaille) {
        this.nom = nom;
        this.detaille = detaille;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDetaille() {
        return detaille;
    }

    public void setDetaille(String detaille) {
        this.detaille = detaille;
    }
}
