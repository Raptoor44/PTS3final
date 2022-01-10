package com.example.pts3.activiteesImportantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pts3.R;

public class Accessibilite extends AppCompatActivity {

    private ImageButton fleche_retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibilite);

        this.fleche_retour = findViewById(R.id.id_activity_accessibilite_fleche_retour);

        this.fleche_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Parametres.class);
                startActivity(intent);
            }
        });
    }
}