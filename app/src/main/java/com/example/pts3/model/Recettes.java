package com.example.pts3.model;

import java.util.LinkedList;
import java.util.List;

public class Recettes {


    private static List<Recette> recettes = new LinkedList<Recette>();


    public static List<Recette> getRecettes() {
        return recettes;
    }

    public static void setRecettes(List<Recette> recettes) {
        Recettes.recettes = recettes;
    }
}
