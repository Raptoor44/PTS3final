package com.example.pts3.Conteneur;

import android.content.Intent;
import android.widget.Toast;

import com.example.pts3.activiteesImportantes.MesConteneurs;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.ListConteneurs;
import com.google.android.material.textfield.TextInputEditText;

public class ValidationConteneur {

    private static ModificationConteneur modificationConteneur;

    public static void extracted(ModificationConteneur modificationConteneur, TextInputEditText nom) {


        modificationConteneur = modificationConteneur;

        String titre_conteneur;
        titre_conteneur = nom.getText().toString();

        boolean isValid = true;

        if (titre_conteneur.equals("")) {
            erreur_saisie();
            isValid = false;
        }

        for (Conteneurs conteneur : ListConteneurs.getConteneursList()) {
            if (conteneur.getNom().equals(titre_conteneur)) {
                erreur_saisie();
                isValid = false;

            }
        }

        if (isValid) {

            for (Conteneurs conteneur : ListConteneurs.getConteneursList()) {
                if (conteneur.isIsvalid()) {
                    conteneur.setNom(titre_conteneur);

                }
            }


            Intent intent = new Intent(modificationConteneur.getApplicationContext(), MesConteneurs.class);
            modificationConteneur.startActivity(intent);

        }

    }


    private static void erreur_saisie() {
        Toast.makeText(modificationConteneur.getApplicationContext(), "Erreur, un conteneur du même nom existe déjà", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(modificationConteneur.getApplicationContext(), Creer_conteneur.class);
        modificationConteneur.startActivity(intent);

        modificationConteneur.finish();
    }

}
