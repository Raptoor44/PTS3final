package com.example.pts3;

import static com.example.pts3.model.ListConteneurs.conteneursList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.pts3.model.CustomListConteneurs;
import com.example.pts3.model.CustomListRecettes;
import com.example.pts3.model.Recettes;

public class Idee_recettes extends AppCompatActivity {


    private ImageButton boutton_retour;
    private Button ajouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idee_recettes);

        this.boutton_retour = findViewById(R.id.id_activity_frigo_boutton_retour);

        this.boutton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.id_activity_idee_recettes_listView);


        CustomListRecettes adapter = new CustomListRecettes(this.getApplicationContext(), Recettes.getRecettes());

        listView.setAdapter(adapter);


        this.ajouter = findViewById(R.id.id_activity_idee_recettes_ajouter);

        this.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AjouterRecette.class);
                startActivity(intent);
                finish();
            }
        });
    }


}