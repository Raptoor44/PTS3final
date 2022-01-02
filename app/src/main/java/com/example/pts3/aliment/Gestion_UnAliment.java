package com.example.pts3.aliment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pts3.Frigo;
import com.example.pts3.R;
import com.example.pts3.model.Aliment;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.List_conteneurs;

public class Gestion_UnAliment extends AppCompatActivity {


    private ImageButton supprimer;
    private Button modifier_produit;
    private ImageButton retour;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_un_aliment);

        this.supprimer = findViewById(R.id.id_activity_gestion_un_aliment_supprimer);
        this.modifier_produit = findViewById(R.id.id_activity_gestion_un_aliment_modifier_le_produit);
        this.retour = findViewById(R.id.id_activity_gestion_un_aliment_retour);


        this.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Conteneurs conteneur : List_conteneurs.getConteneursList()) {
                    if (conteneur.isIsvalid() == true) {
                        for (Aliment aliment : conteneur.getAliments()) {
                            if (aliment.getIsvalide() == true) {
                                conteneur.getAliments().remove(aliment);
                                conteneur.setStatic_id_aliment(conteneur.getStatic_id_aliment() - 1);



                                Intent intent = new Intent(getApplicationContext(), Frigo.class);
                                startActivity(intent);
                                finish();
                            }
                        }


                    }

                }


            }
        });



        this.modifier_produit = findViewById(R.id.id_activity_gestion_un_aliment_modifier_le_produit);

        this.modifier_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ModificationUnAliment.class);
                startActivity(intent);

                finish();
            }
        });

        this.retour = findViewById(R.id.id_activity_gestion_un_aliment_retour);

        this.retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Frigo.class);
                startActivity(intent);

                finish();
            }
        });
    }
}