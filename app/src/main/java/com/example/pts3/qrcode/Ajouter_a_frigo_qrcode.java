package com.example.pts3.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pts3.R;

public class Ajouter_a_frigo_qrcode extends AppCompatActivity {

    private Button scan;
    private String information_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_afrigo_qrcode);


        this.scan = findViewById(R.id.id__activity_ajouter_afrigo_qrcode_scanner);

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

            }

            if (resultCode == RESULT_CANCELED) {
                onBackPressed(); // revebu à l'activité précedebt
            }
        }


    }
}