package com.example.pts3.Conteneur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pts3.Mes_conteneurs;
import com.example.pts3.R;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.List_conteneurs;

public class Gestion_unConteneur extends AppCompatActivity {



    private ImageButton retour;
    private ImageButton supprimer;
    private Button modifier;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_un_conteneur);

        this.supprimer = findViewById(R.id.id_activity_gestion_un_conteneur_supprimer);
        this.modifier = findViewById(R.id.id_activity_gestion_gestion_un_conteneur_modifier);
        this.retour = findViewById(R.id.id_activity_gestion_un_conteneur_retour);


        this.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Conteneurs conteneur : List_conteneurs.getConteneursList()){
                    if(conteneur.isIsvalid() == true){
                        List_conteneurs.getConteneursList().remove(conteneur);

                        Conteneurs.setId(Conteneurs.getId()-1);

                        Intent intent = new Intent (getApplicationContext(), Mes_conteneurs.class);
                        startActivity(intent);
                    }
                }
            }
        });

        this.modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ModificationConteneur.class);
                startActivity(intent);
                finish();
            }
        });

        this.retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Conteneurs.class);
                startActivity(intent);
                finish();
            }
        });

    }
}