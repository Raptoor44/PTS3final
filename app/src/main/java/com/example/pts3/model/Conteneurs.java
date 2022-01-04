package com.example.pts3.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Conteneurs {

    private String nom;
    private ArrayList<Aliment> aliments;
    private boolean isvalid;
    private static int id = 0;
    private int uni_id;
    private int static_id_aliment = 0;


    private List<String> categories;

    public Conteneurs(String nom) {
        this.aliments = new ArrayList<Aliment>();
        this.nom = nom;
        this.uni_id = id;
        id++;


        this.categories = new LinkedList<String>();
    }

    public String getNom() {
        return nom;
    }


    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public int getUni_id() {
        return uni_id;
    }

    public ArrayList<Aliment> getAliments() {
        return aliments;
    }

    public int getStatic_id_aliment() {
        return static_id_aliment;

    }

    public void setStatic_id_aliment(int static_id_aliment) {
        this.static_id_aliment = static_id_aliment;
    }

    public static void setId(int id) {
        Conteneurs.id = id;
    }

    public static int getId() {
        return id;
    }


    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void refreshCategorie() {


        this.categories.clear();

        for (Aliment aliment : this.aliments) {
            this.categories.add(aliment.getCategorie());
        }
        this.categories = removeDuplicates(this.categories);

    }

    public static <String> ArrayList<String> removeDuplicates(List<String> list) {

        // Create a new ArrayList
        ArrayList<String> newList = new ArrayList<String>();

        // Traverse through the first list
        for (String element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }
}
