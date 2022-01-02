package com.example.pts3;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pts3.aliment.Ajouter_a_frigo_general;
import com.example.pts3.aliment.Gestion_UnAliment;
import com.example.pts3.model.Aliment;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.Custom_list_aliment;
import com.example.pts3.model.List_conteneurs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Frigo extends AppCompatActivity {

    private ImageButton retour;
    private Button ajouter_produit;
    private Button tri_ordre_alpha;
    private Button tri_ordre_date;

    private Custom_list_aliment adapter;

    private EditText etSearch;
    private ListView listView;

    private Spinner spinner;


    private CheckBox checkCategorie;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frigo);

        etSearch = (EditText) findViewById(R.id.id_activity_frigo_searchView);
        listView = findViewById(R.id.id_activity_frigo_listview_aliment);

        this.checkCategorie = findViewById(R.id.id_activity_frigo_recherche_categorie);

        this.ajouter_produit = findViewById(R.id.id_activity_frigo_ajouter_produit);
        this.ajouter_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Ajouter_a_frigo_general.class);
                startActivity(intent);
                finish();
            }
        });


        this.tri_ordre_alpha = findViewById(R.id.id_activity_frigo_tri_ordre_alphabetique);
        this.tri_ordre_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conteneurs conteneur_ = initConteneur();

                Conteneurs listAliment;

                listAliment = conteneur_;


                Collections.sort(listAliment.getAliments(), ALPHABETICAL_ORDER1);

                adapter = new Custom_list_aliment(getApplicationContext(), listAliment.getAliments());

                listView.setAdapter(adapter);


            }
        });


        this.tri_ordre_date = findViewById(R.id.id_activity_frigo_tri_par_date);

        this.tri_ordre_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conteneurs conteneur_ = initConteneur();

                Conteneurs listAliment;

                listAliment = conteneur_;


                Collections.sort(listAliment.getAliments(), TRI_DATE);

                adapter = new Custom_list_aliment(getApplicationContext(), listAliment.getAliments());

                listView.setAdapter(adapter);
            }
        });


        this.checkCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCategorie.isChecked() == false) {
                    initAllAliment();

                }
            }
        });


        Conteneurs conteneur_ = initConteneur();
        initAllAliment();


        Conteneurs finalConteneur_ = conteneur_;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                for (Aliment aliment : finalConteneur_.getAliments()) {


                    Log.e("position layout ", position + "");
                    Log.e("id aliment ", aliment.getId() + "");

                    if (position == aliment.getId()) {
                        aliment.setIsvalide(true);
                    }

                    Intent intent = new Intent(getApplicationContext(), Gestion_UnAliment.class);
                    startActivity(intent);
                    finish();

                }

            }
        });


        this.retour = findViewById(R.id.id_activity_frigo_boutton_retour);

        this.retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mes_conteneurs.class);
                startActivity(intent);
                finish();
            }
        });


// Add Text Change Listener to EditText
        etSearch.addTextChangedListener(new TextWatcher() {


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter


                ArrayList<Aliment> alimentFiltre = new ArrayList<Aliment>();


                for (Aliment permute : conteneur_.getAliments()) {
                    if (permute.getNom().contains(s)) {
                        alimentFiltre.add(permute);
                    }
                }


                adapter = new Custom_list_aliment(getApplicationContext(), alimentFiltre);


                listView.setAdapter(adapter);


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        if (conteneur_.getAliments().size() != 0) {


            conteneur_.refreshCategorie();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, conteneur_.getCategories()
            );

            // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            this.spinner = (Spinner) findViewById(R.id.id_activity_frigo_spinner);

            this.spinner.setAdapter(adapter);

            // When user select a List-Item.
            this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    onItemSelectedHandler(parent, view, position, id, conteneur_);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void initAllAliment() {
        Conteneurs conteneur_ = initConteneur();
        adapter = new Custom_list_aliment(getApplicationContext(), conteneur_.getAliments());

        listView.setAdapter(adapter);
    }

    @Nullable
    private Conteneurs initConteneur() {
        Conteneurs conteneur_ = null;

        for (Conteneurs conteneur : List_conteneurs.getConteneursList()) {
            if (conteneur.isIsvalid() == true) {
                conteneur_ = conteneur;
            }


        }
        return conteneur_;
    }


    //TEST d'ajout aliment rapide
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void _aliment() {
        Aliment aliment = new Aliment("test", 50, "unite", Calendar.getInstance().getTime());

        for (Conteneurs conteneur : List_conteneurs.getConteneursList()) {
            if (conteneur.isIsvalid() == true) {

                aliment.setId(conteneur.getStatic_id_aliment());
                conteneur.setStatic_id_aliment(conteneur.getStatic_id_aliment() + 1);
                conteneur.getAliments().add(aliment);

            }
        }
    }

    Comparator<Aliment> ALPHABETICAL_ORDER1 = new Comparator<Aliment>() {
        public int compare(Aliment object1, Aliment object2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(object1.getNom(), object2.getNom());
            return res;
        }
    };


    Comparator<Aliment> TRI_DATE = new Comparator<Aliment>() {
        @Override
        public int compare(Aliment object1, Aliment object2) {
            return object1.getDate_peremption().compareTo(object2.getDate_peremption());
        }
    };

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id, Conteneurs conteneur_) {
        ArrayList<Aliment> alimentFiltre = new ArrayList<Aliment>();

        for (Aliment permute : conteneur_.getAliments()) {
            if (permute.getCategorie().equals(conteneur_.getCategories().get(position))) {
                alimentFiltre.add(permute);
            }
        }

        if (checkCategorie.isChecked() == true) {
            Custom_list_aliment adapt;

            adapt = new Custom_list_aliment(getApplicationContext(), alimentFiltre);

            listView.setAdapter(adapt);
        }

    }

}