package com.sosaley.hapbattery.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sosaley.hapbattery.R;
import com.sosaley.hapbattery.menu.QRDisplayActivity;

public class SigninActivity extends AppCompatActivity {

    TextView signInSignUp,signInForgetPassword;

    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signInSignUp=(TextView)findViewById(R.id.signInSignUp);
        signInForgetPassword=(TextView)findViewById(R.id.signInForgetPassword);

        signInBtn=(Button)findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SigninActivity.this, QRDisplayActivity.class);
                startActivity(intent);



            }
        });

        signInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSignUpActivity();
            }
        });

        signInForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchForgetPasswordActivity();
            }
        });

    }

    private void launchForgetPasswordActivity() {



    }

    private void launchSignUpActivity() {

        Intent intent=new Intent(SigninActivity.this,SignupActivity.class);
        startActivity(intent);

    }
}