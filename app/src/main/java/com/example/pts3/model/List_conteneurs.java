package com.example.pts3.model;

import java.util.ArrayList;
import java.util.List;

public class List_conteneurs {

    public static List<Conteneurs> conteneursList = new ArrayList<Conteneurs>();

    private static String name = null;


    public static List<Conteneurs> getConteneursList() {
        return conteneursList;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        List_conteneurs.name = name;
    }
}
