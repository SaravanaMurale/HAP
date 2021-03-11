package com.sosaley.hap.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;
import com.sosaley.hap.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        zXingScannerView = new ZXingScannerView(QRScannerActivity.this);

        setContentView(zXingScannerView);


    }

    @Override
    public void handleResult(Result result) {

        System.out.println("BarCodeFormet" + result.getBarcodeFormat().name());
        System.out.println("StringFormetData" + result.getText().toString());

        byte[] bytes = result.getRawBytes();

        System.out.println(bytes.length);

        result.getText().toString();


        //result.addResultPoints(ResultPoint[] result);

        QRDisplayActivity.displayQR.setText(result.getText());

        onBackPressed();


    }

    @Override
    protected void onResume() {
        super.onResume();

        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();

        zXingScannerView.stopCamera();
    }





}