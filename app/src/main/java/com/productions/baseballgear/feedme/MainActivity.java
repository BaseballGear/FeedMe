package com.productions.baseballgear.feedme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    Button loginButtonV;
    Button registerButtonV;
    TextView emailText;
    TextView passwordText;
    String newUID;

    Firebase myFirebaseRef = new Firebase("https://feedmefood.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        loginButtonV = (Button) findViewById(R.id.loginButton);
        registerButtonV = (Button) findViewById(R.id.registerButton);
        emailText = (TextView) findViewById(R.id.loginText);
        passwordText = (TextView) findViewById(R.id.passwordText);

        loginButtonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hello", "Click On");

                //Taking the Email and Password, checking it against firebase
                myFirebaseRef.authWithPassword(emailText.getText().toString(), passwordText.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        //Auth was good, we login to the homeScreen
                        Log.d("hello", "hit firebase");
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                        setLoginButtonV(loginButtonV);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        Log.i("Error", "didn't hit firebase");
                    }
                });
            }
        });

        registerButtonV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setRegisterButtonV(registerButtonV);
            }

        });
    }

    //This gets called when logging in and takes you to the home screen
    public void setLoginButtonV(View v)
    {
        if(v.getId() == R.id.loginButton)
        {
            Intent i = new Intent (MainActivity.this, homeScreen.class);
            i.putExtra("theUID",newUID);
            startActivity(i);
        }
    }

    //This gets called when registering a new account and takes you to the register screen
    public void setRegisterButtonV(View v)
    {
        if(v.getId() == R.id.registerButton)
        {
            Intent i = new Intent (MainActivity.this, registerScreen.class);
            startActivity(i);
        }
    }
}
