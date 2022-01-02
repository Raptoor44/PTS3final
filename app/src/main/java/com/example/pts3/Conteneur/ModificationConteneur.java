package com.example.pts3.Conteneur;

import static com.example.pts3.Conteneur.ValidationConteneur.extracted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pts3.R;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.List_conteneurs;
import com.google.android.material.textfield.TextInputEditText;

public class ModificationConteneur extends AppCompatActivity {

    private TextInputEditText nom;
    private Button ajouter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_conteneur);

        this.nom = findViewById(R.id.id_activity_creer_conteneur_text_input);

        for(Conteneurs conteneur : List_conteneurs.getConteneursList()){
            if(conteneur.isIsvalid()){
                this.nom.setText(conteneur.getNom());
            }
        }

        this.ajouter = findViewById(R.id.id_activity_creer_conteneur_bouton_creer_conteneur);

        this.ajouter.setText("Modifier");

        this.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extracted(ModificationConteneur.this, nom);
            }
        });



    }




}