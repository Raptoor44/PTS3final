package com.example.pts3.aliment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pts3.Frigo;
import com.example.pts3.R;
import com.example.pts3.model.Aliment;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.List_conteneurs;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ajouter_a_frigo_manuel extends AppCompatActivity {

    private Button ajouter;
    private TextInputEditText titre;
    private TextInputEditText quantite;
    private TextInputEditText date_peremption;
    private TextInputEditText categorie;

    private ImageButton retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_afrigo_manuel);

        this.titre = findViewById(R.id.id_activity_modification_un_aliment_nom);
        this.quantite = findViewById(R.id.id_activity_modification_un_aliment_quantite);
        this.date_peremption = findViewById(R.id.id_activity_modification_un_aliment_date_peremption);
        this.categorie = findViewById(R.id.id_activity_modification_un_aliment_categorie);


        this.ajouter = findViewById(R.id.id_activity_modification_un_aliment_ajouter);

        //    public Aliment(String nom, int quantité, String unite_quantite) {

        this.ajouter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                int quantite_ = 0;
                boolean valide = false;
                try {
                    quantite_ = Integer.parseInt(quantite.getText().toString());
                    valide = true;
                } catch (NumberFormatException e) {

                    Toast.makeText(getApplicationContext(), "Erreur, vous avez rentré un nombre trop important de caractères ou vous " +
                            "avez mal saisis la quantite (donné numérique)", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Ajouter_a_frigo_manuel.class);
                    startActivity(intent);

                    finish();

                }

                Date date = null;
                boolean valide2_ = false;

                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(date_peremption.getText().toString());
                    valide2_ = true;


                    Log.e("La date : " ,date.toString());
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "Erreur, vous n'avez pas entrez une date valide !, Veuillez recommencer",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), Ajouter_a_frigo_manuel.class);
                    startActivity(intent);

                    finish();

                }


                if (valide == true && valide2_ == true) {
                    Aliment aliment = new Aliment(titre.getText().toString(), quantite_, categorie.getText().toString(), date);
                    for (Conteneurs conteneur : List_conteneurs.getConteneursList()) {
                        if (conteneur.isIsvalid() == true) {

                            aliment.setId(conteneur.getStatic_id_aliment());
                            conteneur.setStatic_id_aliment(conteneur.getStatic_id_aliment() + 1);
                            conteneur.getAliments().add(aliment);

                        }
                    }

                    Intent intent = new Intent(getApplicationContext(), Frigo.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        this.retour = findViewById(R.id.id_activity_ajouter_afrigo_manuel_retour);

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