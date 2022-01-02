package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Parametres extends AppCompatActivity {


    private ImageButton fleche_retour;
    private Button accessibilité;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        this.fleche_retour = findViewById(R.id.id_activity_parametres_fleche_retour);

        this.fleche_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        this.accessibilité= findViewById(R.id.id_activity_parametres_boutton_accessibilité);

        this.accessibilité.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Accessibilite.class);
                startActivity(intent);
            }
        });

    }
}