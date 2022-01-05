package com.example.pts3;

import static com.example.pts3.model.ListConteneurs.conteneursList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.pts3.Conteneur.Creer_conteneur;
import com.example.pts3.model.Conteneurs;
import com.example.pts3.model.CustomListConteneurs;
import com.example.pts3.model.ListConteneurs;

public class MesConteneurs extends AppCompatActivity {

    private ImageButton fleche_retour;
    private Button ajouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_conteneurs);



        this.descative_all_conteneurs();

        this.fleche_retour = findViewById(R.id.id_activity_mes_conteneurs_bouton_retour);

        final ListView listView = (ListView) findViewById(R.id.id_activity_mes_conteneurs_list_view);


        CustomListConteneurs adapter = new CustomListConteneurs(this.getApplicationContext(), conteneursList );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), Frigo.class);
            startActivity(intent);

            for (Conteneurs conteneur : ListConteneurs.getConteneursList()) {

                if (conteneur.getUni_id() == position) {
                    conteneur.setIsvalid(true);
                }

            }


            finish();

        }
    });

        fleche_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        this.ajouter = findViewById(R.id.id_activity_mes_conteneurs_ajouter);

        this.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Creer_conteneur.class);
                startActivity(intent);
            }
        });

    }

    private void descative_all_conteneurs(){
        for (Conteneurs conteneur : ListConteneurs.getConteneursList()) {
            conteneur.setIsvalid(false);
        }
    }
}