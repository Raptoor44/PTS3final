package com.example.pts3.aliment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pts3.R;
import com.example.pts3.activiteesImportantes.Frigo;
import com.example.pts3.qrcode.AjouterAFrigoQrcode;

public class AjouterAFrigoGeneral extends AppCompatActivity {


    private Button ajouter_produit;
    private Button ajouter_produit_scan;
    private ImageButton retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_afrigo_general);

        this.ajouter_produit = findViewById(R.id.id_activity_ajouter_afrigo_general_AJOUT_MANUEL);

        this.ajouter_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AjouterAFrigoManuel.class);
                startActivity(intent);
            }
        });

        this.ajouter_produit_scan = findViewById(R.id.id_activity_ajouter_afrigo_general_ajouter_produit_scan);

        this.ajouter_produit_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AjouterAFrigoQrcode.class);
                startActivity(intent);
                finish();
            }
        });

        this.retour = findViewById(R.id.id_activity_ajouter_afrigo_general_bouton_retour);

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