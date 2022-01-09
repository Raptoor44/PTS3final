package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pts3.model.ListConteneurs;
import com.example.pts3.model.Recettes;
import com.example.pts3.outils.Serializer;

public class Creer_nouvelle_liste extends AppCompatActivity {


    private Button creer_nouvelle_liste;
    private ImageButton boutton_retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_nouvelle_liste);


        this.creer_nouvelle_liste = findViewById(R.id.id_activity_creer_nouvelle_liste_boutton_Creer_nouvelle_liste);

        this.creer_nouvelle_liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code à compléter
                Intent intent = new Intent(getApplicationContext(), MesListes.class);

                startActivity(intent);
                finish();

            }
        });

        this.boutton_retour = findViewById(R.id.id_activity_creer_nouvelle_liste_boutton_retour);

        this.boutton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MesListes.class);
                startActivity(intent);
            }
        });


    }
}