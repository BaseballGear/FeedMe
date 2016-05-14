package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by BaseballGear on 4/17/2016.
 */
public class registerScreen extends Activity
{
    Button createAccountBtn;
    TextView createEmailTxt;
    TextView createPassTxt;
    TextView chkEmailTxt;
    TextView chkPassTxt;
    String newUID;
    String theUserEmail;

    Firebase myFirebaseRef = new Firebase("https://feedmefood.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        createAccountBtn = (Button)findViewById(R.id.createAccountButton);
        createEmailTxt = (TextView)findViewById(R.id.regEmailText);
        createPassTxt = (TextView)findViewById(R.id.regPassText);
        chkEmailTxt = (TextView)findViewById(R.id.chkEmailText);
        chkPassTxt = (TextView)findViewById(R.id.chkPassText);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hello", "Click On");

                if (!createEmailTxt.getText().toString().equals(chkEmailTxt.getText().toString())) {
                    errorEmail();
                }
                else if (!createPassTxt.getText().toString().equals(chkPassTxt.getText().toString()))
                {
                    errorPassword();
                }
                else
                {
                    myFirebaseRef.createUser(createEmailTxt.getText().toString(), createPassTxt.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result)
                        {
                            System.out.println("Successfully created user account with uid: " + result.get("uid"));
                            newUID = result.get("uid").toString();
                            theUserEmail = createEmailTxt.getText().toString();
                            setCreateAccountBtn(createAccountBtn);
                        }

                        @Override
                        public void onError(FirebaseError firebaseError)
                        {
                            // there was an error
                            Log.d("Firebase", "Fail");
                        }
                    });
                }
            }
        });


    }


    /*******************/
    /*   Button Setup  */
    /*******************/
    //This gets called when registering a new account and takes you to the register screen
    public void setCreateAccountBtn(View v)
    {
        if(v.getId() == R.id.createAccountButton)
        {
            Intent i = new Intent (registerScreen.this, userInfo.class);
            i.putExtra("theUID",newUID);
            i.putExtra("theUserEmail",theUserEmail);
            startActivity(i);
        }
    }


    /******************/
    /*  ERROR ALERTS  */
    /******************/
    //This will get called if the emails don't match.
    private void errorEmail()
    {
        AlertDialog.Builder emailAlert = new AlertDialog.Builder(this);
        emailAlert.setMessage("Emails do not match, please try again.")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .setTitle("Email Error")
                .create();
        emailAlert.show();
    }
    //This will get called if the passwords don't match
    private void errorPassword()
    {
        AlertDialog.Builder passwordAlert = new AlertDialog.Builder(this);
        passwordAlert.setMessage("Passwords do not match, please try again.")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .setTitle("Password Error")
                .create();
        passwordAlert.show();
    }
}