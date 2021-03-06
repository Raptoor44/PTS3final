package com.example.pts3.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pts3.Activity_static.MainActivity_static;
import com.example.pts3.activiteesImportantes.Idee_recettes;
import com.example.pts3.R;
import com.example.pts3.outils.Serializer;

import java.util.List;

public class CustomListRecettes extends BaseAdapter {
    private List<Recette> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListRecettes(Context applicationContext, List<Recette> conteneursList) {
        this.context = applicationContext;
        this.listData = conteneursList;
        layoutInflater = LayoutInflater.from(applicationContext);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Recette getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {

        convertview = layoutInflater.inflate(R.layout.adaptater_view_recettes, null);

        Recette recette = getItem(position);

        TextView titre = convertview.findViewById(R.id.id_adaptater_view_recettes_titre);
        TextView intule = convertview.findViewById(R.id.id_adaptater_view_recettes_intule);

        titre.setText(this.listData.get(position).getNom());
        intule.setText(this.listData.get(position).getDetaille());

        Button button = convertview.findViewById(R.id.id_adapter_view_recettes_supprimer);

        FrameLayout affiche = convertview.findViewById(R.id.id_adaptater_view_recettes_layout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0 ; i < Recettes.getRecettes().size(); i++){
                    Recettes.getRecettes().remove(i);
                }
                Intent intent = new Intent(MainActivity_static.getMain(), Idee_recettes.class);
                MainActivity_static.getMain().startActivity(intent);
                Serializer.serialize("recettes", Recettes.getRecettes(), MainActivity_static.getMain().getApplicationContext());
                MainActivity_static.getMain().finish();

            }
        });


        return convertview;
    }
}
