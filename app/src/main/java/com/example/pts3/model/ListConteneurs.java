package com.example.pts3.model;

import java.util.ArrayList;
import java.util.List;

public class ListConteneurs {

    public static List<Conteneurs> conteneursList = new ArrayList<Conteneurs>();

    private static String name = null;
    private static String categorie = null;


    public static List<Conteneurs> getConteneursList() {
        return conteneursList;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ListConteneurs.name = name;
    }

    public static void setConteneursList(List<Conteneurs> conteneursList) {
        ListConteneurs.conteneursList = conteneursList;
    }

    public static String getCategorie() {
        return categorie;
    }

    public static void setCategorie(String categorie) {
        ListConteneurs.categorie = categorie;
    }
}
