package com.sosaley.hap.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.sosaley.hap.R;

import static com.sosaley.hap.utils.AppConstant.REQ_CODE;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    SignInButton signInButton;
    GoogleApiClient googleApiClient;

    Button signUpBtn;

    EditText signupMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signInButton=(SignInButton)findViewById(R.id.gmailSignup);
        signUpBtn=(Button)findViewById(R.id.signUpBtn);
        signupMobile=(EditText)findViewById(R.id.signupMobile);

        signInButton.setOnClickListener(this);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                launchOTPActivity();


            }
        });

        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
    }

    private void launchOTPActivity() {

        Intent intent=new Intent(SignupActivity.this,OTPActivity.class);
        intent.putExtra("MOBILE","+919123521374");
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.gmailSignup:
                signIn();
                break;

            /*case R.id.gLogout:
                signOut();
                break;*/
        }
    }

    private void signIn(){

        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }

    private void handleResult(GoogleSignInResult result){

        System.out.println("SuccessResult"+result.isSuccess());

        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();

            signOut();

            System.out.println("Name"+name);

            System.out.println("Email"+email);

            Toast.makeText(SignupActivity.this,"NameAndEmail "+name+" "+email,Toast.LENGTH_LONG).show();


            /*String img_url = account.getPhotoUrl().toString();

            System.out.println("IMAGE"+img_url);

            if(img_url==null){

            }else {
                Glide.with(this).load(img_url).into(profilePic);
            }*/


            System.out.println("Test" + name + " " + email);

           // gName.setText(name);
            //gEmail.setText(email);
            //updateUI(true);
        }
        else {
           // updateUI(false);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE){

            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);

        }
    }

    private void signOut(){

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
               // updateUI(false);
            }
        });

    }
}