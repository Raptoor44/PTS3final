package com.example.pts3.qrcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.Person;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pts3.R;

import com.example.pts3.aliment.Ajouter_a_frigo_manuel;
import com.example.pts3.model.List_conteneurs;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClients;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;


public class Ajouter_a_frigo_qrcode extends AppCompatActivity {

    private Button scan;
    private String information_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_afrigo_qrcode);


        this.scan = findViewById(R.id.id__activity_ajouter_afrigo_qrcode_scanner);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }




        this.scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannerQRCode();
            }
        });
    }


    public void scannerQRCode() {
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "BAR_CODE_MODE");

            startActivityForResult(intent, 0);

        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);

        }


    }

    @SuppressLint("LongLogTag")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String infoDansQRCode = data.getStringExtra("SCAN_RESULT");
                Log.e("Scanner", infoDansQRCode);
                this.information_scan = (data.getStringExtra("SCAN_RESULT"));

                Log.d("information produit scanné : ", this.information_scan.toString());
                try {
                    this.uploadToServer(this.information_scan.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            if (resultCode == RESULT_CANCELED) {
                onBackPressed(); // revebu à l'activité précedebt
            }
        }


    }

    private void uploadToServer(String code) throws IOException, JSONException {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://world.openfoodfacts.org/api/v0/product/" + code + ".json");

// Request parameters and other properties.


//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();


        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    // parsing JSON



                    JSONObject result = new JSONObject(retSrc); //Convert String to JSON Object


                    JSONObject product = result.getJSONObject("product");
                    Object level = product.get("generic_name_fr");


                   Intent intent = new Intent(getApplicationContext(), Ajouter_a_frigo_manuel.class);
                   startActivity(intent);

                    List_conteneurs.setName(level.toString());

                   finish();


                }
            }
        }

    }
}


