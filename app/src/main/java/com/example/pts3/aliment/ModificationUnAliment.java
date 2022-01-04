package com.example.pts3.aliment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pts3.Frigo;
import com.example.pts3.R;
import com.example.pts3.model.Aliment;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.List_conteneurs;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class ModificationUnAliment extends AppCompatActivity {


    private TextInputEditText titre;
    private TextInputEditText quantite;
    private TextInputEditText date_input;
    private TextInputEditText categorie;


    private Button ajouter;
    private ImageButton retour;

    private Spinner spinner;

    private String uniteQuantite;
    private LinkedList<String> listUnite;
    private boolean isValid3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_un_aliment);


        titre = findViewById(R.id.id_activity_modification_un_aliment_nom);
        quantite = findViewById(R.id.id_activity_modification_un_aliment_quantite);
        date_input = findViewById(R.id.id_activity_modification_un_aliment_date_peremption);
        categorie = findViewById(R.id.id_activity_modification_un_aliment_categorie);


        ajouter = findViewById(R.id.id_activity_modification_un_aliment_ajouter);


        this.spinner = findViewById(R.id.id_activity_ajouter_afrigo_manuel_spinner);

        listUnite = new LinkedList<String>();

        listUnite.add("kg");
        listUnite.add("g");
        listUnite.add("unite");
        listUnite.add("ml");
        listUnite.add("mg");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listUnite
        );

        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        this.spinner.setAdapter(adapter);

        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                onItemSelectedHandler(parent, view, position, id);
                isValid3 = true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        for (Conteneurs conteneur_ : List_conteneurs.getConteneursList()) {
            if (conteneur_.isIsvalid() == true) {
                for (Aliment aliment : conteneur_.getAliments()) {
                    if (aliment.getIsvalide() == true) {


                        titre.setText(aliment.getNom());
                        quantite.setText(aliment.getQuantité() + "");

                        /////////
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                        String dateAffiche = formatter.format(aliment.getDate_peremption());


                        date_input.setText(dateAffiche);
                        /////////


                        categorie.setText(aliment.getCategorie());


                    }
                }


            }

        }
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
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(date_input.getText().toString());
                    valide2_ = true;


                    Log.e("La date : ", date.toString());
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "Erreur, vous n'avez pas entrez une date valide !, Veuillez recommencer",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), Ajouter_a_frigo_manuel.class);
                    startActivity(intent);

                    finish();

                }


                if (valide == true && valide2_ == true && isValid3 == true) {

                    for (Conteneurs conteneur : List_conteneurs.getConteneursList()) {
                        if (conteneur.isIsvalid() == true) {

                            for (Aliment aliment : conteneur.getAliments()) {

                                if (aliment.getIsvalide()) {
                                    aliment.setId(conteneur.getStatic_id_aliment());
                                    conteneur.setStatic_id_aliment(conteneur.getStatic_id_aliment() + 1);

                                    aliment.setCategorie(categorie.getText().toString());
                                    aliment.setNom(titre.getText().toString());
                                    aliment.setQuantité(quantite_);
                                    aliment.setDate_peremption(date);
                                    aliment.setUnite_quantite(uniteQuantite);
                                }
                            }
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
                Intent intent = new Intent(getApplicationContext(), Gestion_UnAliment.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        uniteQuantite = this.listUnite.get(position);

    }
}