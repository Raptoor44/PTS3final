package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pts3.model.Recette;
import com.example.pts3.model.Recettes;
import com.google.android.material.textfield.TextInputEditText;

public class AjouterRecette extends AppCompatActivity {


    private TextInputEditText nom;
    private TextInputEditText detaille;
    private Button ajouter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_recette);


        this.ajouter = findViewById(R.id.id_activity_ajouter_recette_boutton_ajouter);

        this.nom = findViewById(R.id.id_activity_ajouter_recette_text_input_nom);
        this.detaille = findViewById(R.id.id_activity_ajouter_recette_text_input_intitule);

        this.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nom.getText().toString() != null && detaille.getText().toString() != null) {
                    Recette recette = new Recette(nom.getText().toString(), detaille.getText().toString());

                    Recettes.getRecettes().add(recette);

                    Intent intent = new Intent(getApplicationContext(), Idee_recettes.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}