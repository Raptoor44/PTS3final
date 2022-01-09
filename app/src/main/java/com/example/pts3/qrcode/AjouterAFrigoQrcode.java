package com.example.pts3.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pts3.R;

import com.example.pts3.aliment.AjouterAFrigoManuel;
import com.example.pts3.model.ListConteneurs;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClients;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

import java.io.InputStream;


public class AjouterAFrigoQrcode extends AppCompatActivity {

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

/*    // Pour le test le test
        try {
            this.uploadToServer("3700496306026");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

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
                onBackPressed(); // revevu à l'activité précedent
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
                    Object name = product.get("generic_name_fr");
                    //Object categories = product.get("categories");


                    Intent intent = new Intent(getApplicationContext(), AjouterAFrigoManuel.class);
                    startActivity(intent);

                    ListConteneurs.setName(name.toString());


                    finish();


                }
            }
        }

    }
}


