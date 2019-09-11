package com.jadaperkasa.barcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button buttonScan;
    private Button createImage;
    private EditText isiText;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonScan = (Button) findViewById(R.id.button2);
        createImage = (Button) findViewById(R.id.button3);

        qrScan = new IntentIntegrator(this);
    }

    public void scanBarcode(View view) {
        new IntentIntegrator(this).initiateScan();
    }

    public void scanBarcodeCustomOptions(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.initiateScan();
    }

    public void scanContinuous(View view) {
        Intent intent = new Intent(this, ContinuousCaptureActivity.class);
        startActivity(intent);
    }

    public void scanMarginScanner(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(SmallCaptureActivity.class);
        integrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void createBarcode(View view) {
        qrScan.initiateScan();
    }


    public void tabcompat(View view) {
        Intent intent = new Intent(this, Barcodetampil.class);
        startActivity(intent);
    }

    public void scanCustomScanner(View view) {
        new IntentIntegrator(this).setOrientationLocked(false).setCaptureActivity(CustomScannerActivity.class).initiateScan();

    }


}
