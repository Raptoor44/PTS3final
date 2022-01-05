package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MesListes extends AppCompatActivity {


    private ImageButton boutton_retour;
    private Button boutton_ajouter_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_listes);


        this.boutton_retour = findViewById(R.id.id_activity_mes_listes_boutton_retour);
        this.boutton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        this.boutton_ajouter_list = findViewById(R.id.id_activity_mes_listes_ajouter);

        this.boutton_ajouter_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Creer_nouvelle_liste.class);
                startActivity(intent);
            }
        });
    }
}