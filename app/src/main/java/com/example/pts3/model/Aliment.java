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
    public Aliment(String nom, int quantité, String categorie, Date date_peremption, String unite_quantite) {
        this.nom = nom;
        this.quantité = quantité;
        this.categorie = categorie;
        this.date_peremption = date_peremption;
        this.date_ajout = LocalDateTime.now();
        this.unite_quantite = unite_quantite;

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Aliment(String nom , Date date_peremption) {
        this.nom = nom;
        this.quantité = 1;
        this.unite_quantite = " " ;
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


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public void setUnite_quantite(String unite_quantite) {
        this.unite_quantite = unite_quantite;
    }

    public void setDate_peremption(Date date_peremption) {
        this.date_peremption = date_peremption;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Boolean getvalidePeremption() {
        Date dateDuJour = new Date(System.currentTimeMillis());
        if(date_peremption.after(dateDuJour)){
            setIsvalide(false);
        }
        else{
            setIsvalide(true);
        }
        return isvalide;
    }
}
