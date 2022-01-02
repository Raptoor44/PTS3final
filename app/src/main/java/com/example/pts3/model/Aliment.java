package com.example.pts3.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.Date;

public class Aliment {

    private String nom;
    private int quantité;
    private String unite_quantite;
    private LocalDateTime date_ajout;
    private Date date_peremption;
    private Boolean isvalide = false;

    private String categorie;

    private int id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Aliment(String nom, int quantité, String categorie, Date date_peremption) {
        this.nom = nom;
        this.quantité = quantité;
        this.categorie = categorie;
        this.date_peremption = date_peremption;
        this.date_ajout = LocalDateTime.now();

    }

    public String getNom() {
        return nom;
    }

    public int getQuantité() {
        return quantité;
    }

    public String getUnite_quantite() {
        return unite_quantite;
    }

    public LocalDateTime getDate_ajout() {
        return date_ajout;
    }

    public Date getDate_peremption() {
        return date_peremption;
    }

    public Boolean getIsvalide() {
        return isvalide;
    }

    public void setIsvalide(Boolean isvalide) {
        this.isvalide = isvalide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
