package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pts3.Activity_static.MainActivity_static;

public class MainActivity extends AppCompatActivity {


    private Button mes_conteneurs;
    private Button ma_liste_de_cources;
    private Button idees_recettes;

    private ImageButton engrenage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivity_static.setMain(this);



            mes_conteneurs = findViewById(R.id.id_activity_main_mes_conteneurs);
            ma_liste_de_cources = findViewById(R.id.id_activity_main_ma_liste_de_courses);
            idees_recettes = findViewById(R.id.id_activity_main_idee_recettes);
            engrenage = findViewById(R.id.id_activity_main_engrenage_paramètres);

            mes_conteneurs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MesConteneurs.class);
                    startActivity(intent);

                }
            });

            ma_liste_de_cources.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MesListes.class);
                    startActivity(intent);
                }
            });

            idees_recettes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Idee_recettes.class);
                    startActivity(intent);
                }
            });

            this.engrenage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Parametres.class);
                    startActivity(intent);
                }
            });

    }
}