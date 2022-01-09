package com.example.pts3.Conteneur;

import static com.example.pts3.Conteneur.ValidationConteneur.extracted;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pts3.R;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.ListConteneurs;
import com.example.pts3.outils.Serializer;
import com.google.android.material.textfield.TextInputEditText;

public class ModificationConteneur extends AppCompatActivity {

    private TextInputEditText nom;
    private Button ajouter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_conteneur);

        this.nom = findViewById(R.id.id_activity_creer_conteneur_text_input);

        for (Conteneurs conteneur : ListConteneurs.getConteneursList()) {
            if (conteneur.isIsvalid()) {
                this.nom.setText(conteneur.getNom());
                Serializer.serialize("file", ListConteneurs.getConteneursList(), getApplicationContext());
            }
        }

        this.ajouter = findViewById(R.id.id_activity_creer_conteneur_bouton_creer_conteneur);

        this.ajouter.setText("Modifier");

        this.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extracted(ModificationConteneur.this, nom);
                Serializer.serialize("file", ListConteneurs.getConteneursList(), getApplicationContext());
            }

        });


    }


}