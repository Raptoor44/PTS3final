package com.example.pts3.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.pts3.Activity_static.MainActivity_static;
import com.example.pts3.Conteneur.Gestion_unConteneur;
import com.example.pts3.Frigo;
import com.example.pts3.Mes_conteneurs;
import com.example.pts3.R;

import java.util.List;

public class Custom_list_conteneurs extends BaseAdapter {


    private List<Conteneurs> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public Custom_list_conteneurs(Context applicationContext, List<Conteneurs> conteneursList) {
        this.context = applicationContext;
        this.listData = conteneursList;
        layoutInflater = LayoutInflater.from(applicationContext);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Conteneurs getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {

        convertview = layoutInflater.inflate(R.layout.adaptater_view_conteneurs, null);

        Conteneurs conteneur = getItem(position);

        TextView titre = convertview.findViewById(R.id.id_adaptater_view_conteneurs);

        Button button = convertview.findViewById(R.id.id_adaptater_view_conteneurs_modifier_supprimer);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Conteneurs conteneur : List_conteneurs.getConteneursList()) {
                    if (position == conteneur.getUni_id()) {
                        conteneur.setIsvalid(true);
                    }
                }
                Intent intent = new Intent(MainActivity_static.getMain().getApplicationContext(), Gestion_unConteneur.class);
                MainActivity_static.getMain().startActivity(intent);


            }
        });
        Button acceder = convertview.findViewById(R.id.id_adapter_view_conteneurs_acceder);

        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Conteneurs conteneur : List_conteneurs.getConteneursList()) {
                    if (position == conteneur.getUni_id()) {
                        conteneur.setIsvalid(true);
                    }
                    Intent intent = new Intent(MainActivity_static.getMain().getApplicationContext(), Frigo.class);
                    MainActivity_static.getMain().startActivity(intent);
                }
            }
        });


        titre.setText(conteneur.getNom());

        return convertview;
    }
}
