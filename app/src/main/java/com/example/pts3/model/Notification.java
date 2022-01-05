package com.example.pts3.model;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private boolean notifier;
    private List<Conteneurs> lesConteneurs;
    private String message;


    public Notification(List<Conteneurs> lesConteneurs) {
        this.lesConteneurs = lesConteneurs;
        this.notifier = false;
        this.message = "Tout est ok";


    }

    public List<Aliment> checkForNotif() {
        List<Aliment> lesAliments = new ArrayList<>();

        for (Conteneurs conteneur : ListConteneurs.getConteneursList()) {

            for (Aliment aliment : conteneur.getAliments()) {
                if (!aliment.getvalidePeremption()) {
                    setNotifier(true);
                    lesAliments.add(aliment);
                }


            }
            if (lesAliments == null) {
                setNotifier(false);
            }

        }

        return lesAliments;
    }

    public void notifier(List<Aliment> aliments) {
        for (Aliment aliment : aliments) {
            this.message += aliment.getNom() + " est périmé \n";
        }
    }

    public String getMessage() {
        return message;
    }

    public boolean isNotifier() {
        return notifier;
    }

    public void setNotifier(boolean notifier) {
        this.notifier = notifier;
    }
}