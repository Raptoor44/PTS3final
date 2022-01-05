package com.example.pts3.model;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.pts3.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomListAliment extends BaseAdapter  {


    private ArrayList<Aliment> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    private ArrayList<Aliment> mDisplayedValues;
    private ArrayList<Aliment> mOriginalValues;

    public CustomListAliment(ArrayList<Aliment> listData, LayoutInflater layoutInflater, Context context) {
        this.listData = listData;
        this.layoutInflater = layoutInflater;
        this.context = context;
    }


    public CustomListAliment(Context applicationContext, ArrayList<Aliment> listData) {
        this.context = applicationContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Aliment getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {

        convertview = layoutInflater.inflate(R.layout.adaptater_view_aliment, null);

        Aliment aliment = getItem(position);

        TextView titre_layout = convertview.findViewById(R.id.id_adaptater_view_aliment_titre_produit);
        TextView date_peremption_layout = convertview.findViewById(R.id.id_adapter_view_aliment_date_peremption);
        TextView quantite_layout = convertview.findViewById(R.id.id_adapter_view_aliment_quantite);
        TextView categorie = convertview.findViewById(R.id.id_adapter_view_aliment_categorie);


        categorie.setText(aliment.getCategorie());

        titre_layout.setText(aliment.getNom());


        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String dateAffiche = formatter.format(aliment.getDate_peremption());

        date_peremption_layout.setText(dateAffiche);

        quantite_layout.setText(aliment.getQuantité() + " "+aliment.getUnite_quantite());


        //   Log.e("nom de l'aliment", aliment.getNom());

        return convertview;
    }


    //LA SUITE : PARTIE QUI GERE LA RECHERCHE D'UN PRODUIT :
/*
    @Override
    public Filter getFilter() {
        Conteneurs conteneur = null;

        for (Conteneurs conteneur_ : List_conteneurs.getConteneursList()) {
            if (conteneur_.isIsvalid() == true) {
                conteneur = conteneur_;
            }
        }


        Conteneurs finalConteneur = conteneur;
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDisplayedValues = (ArrayList<Aliment>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Aliment> FilteredArrList = new ArrayList<Aliment>();

                if (mOriginalValues == null) {
                    Log.e("Liste aliment", finalConteneur.getAliments().get(0).getNom());
                    mOriginalValues = finalConteneur.getAliments(); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/

    /*
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getNom();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Aliment(mOriginalValues.get(i).getNom(),
                                    mOriginalValues.get(i).getQuantité(),
                                    mOriginalValues.get(i).getUnite_quantite(),  mOriginalValues.get(i).getDate_peremption()));
                            //  public Aliment(String nom, int quantité, String unite_quantite) {


                                Log.e("ALIMENT DANS LA LISTE ", FilteredArrList.get(i).getNom() + ": nom bon");




                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }

                Log.e("results", results.toString());
                return results;
            }
        };
        return filter;

    }*/


}





