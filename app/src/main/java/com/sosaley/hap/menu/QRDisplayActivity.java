package com.sosaley.hap.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sosaley.hap.R;
import com.sosaley.hap.utils.AppConstant;
import com.sosaley.hap.utils.PermissionUtils;

public class QRDisplayActivity extends AppCompatActivity {

    Button startQR;
    public static TextView displayQR;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_display);

        startQR=(Button) findViewById(R.id.startQR);
        displayQR=(TextView)findViewById(R.id.displayQR);

        startQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PermissionUtils.hasPermission(QRDisplayActivity.this, Manifest.permission.CAMERA)) {


                    PermissionUtils.requestPermissions(QRDisplayActivity.this, new String[]{Manifest.permission.CAMERA}, AppConstant.CAMERA_ACCESS);

                } else {
                    callActivity();
                }


            }
            });




    }

    private void callActivity() {

        Intent intent=new Intent(QRDisplayActivity.this,QRScannerActivity.class);
        startActivity(intent);

    }
}