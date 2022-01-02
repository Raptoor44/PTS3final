package com.example.pts3.aliment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pts3.R;
import com.example.pts3.aliment.Ajouter_a_frigo_manuel;
import com.example.pts3.qrcode.Ajouter_a_frigo_qrcode;

public class Ajouter_a_frigo_general extends AppCompatActivity {


    private Button ajouter_produit;
    private Button ajouter_produit_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_afrigo_general);

        this.ajouter_produit = findViewById(R.id.id_activity_ajouter_afrigo_general_AJOUT_MANUEL);

        this.ajouter_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Ajouter_a_frigo_manuel.class);
                startActivity(intent);
            }
        });

        this.ajouter_produit_scan = findViewById(R.id.id_activity_ajouter_afrigo_general_ajouter_produit_scan);

        this.ajouter_produit_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Ajouter_a_frigo_qrcode.class);
                startActivity(intent);
                finish();
            }
        });
    }
}