package com.example.pts3.Conteneur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pts3.activiteesImportantes.MesConteneurs;
import com.example.pts3.R;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.ListConteneurs;
import com.example.pts3.outils.Serializer;
import com.google.android.material.textfield.TextInputEditText;

public class Creer_conteneur extends AppCompatActivity {

    private TextInputEditText text;
    private Button ajouter;
    private ImageButton retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_conteneur);


        this.ajouter = findViewById(R.id.id_activity_creer_conteneur_bouton_creer_conteneur);
        this.text = findViewById(R.id.id_activity_creer_conteneur_text_input);

        this.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titre_conteneur;
                titre_conteneur = text.getText().toString();

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

                    ListConteneurs.getConteneursList().add(new Conteneurs(titre_conteneur));


                    Serializer.serialize("file", ListConteneurs.getConteneursList(), getApplicationContext());

                    Intent intent = new Intent(getApplicationContext(), MesConteneurs.class);
                    startActivity(intent);

                }
            }
        });

        this.retour = findViewById(R.id.id_activity_creer_conteneur_bouton_retour);

        this.retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MesConteneurs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void erreur_saisie() {
        Toast.makeText(getApplicationContext(), "Erreur, un conteneur du même nom existe déjà", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Creer_conteneur.class);
        startActivity(intent);

        finish();
    }
}