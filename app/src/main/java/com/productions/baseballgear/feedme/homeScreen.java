package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class homeScreen extends Activity
{
    Firebase myFirebaseRef = new Firebase("https://feedmefood.firebaseio.com/");
    String uid = myFirebaseRef.getAuth().getUid();

    Button delivererBtn;
    Button customerBtn;
    Button toLogOut;
    TextView welcomeUser;
    Boolean didLogOut = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onStart()
    {
        delivererBtn = (Button)findViewById(R.id.deliverBtn);
        customerBtn = (Button)findViewById(R.id.customerBtn);
        toLogOut = (Button)findViewById(R.id.logOut);
        welcomeUser = (TextView)findViewById(R.id.UserWelcome);
        super.onStart();

        final Firebase fName = myFirebaseRef.child("User Accounts").child(uid).child("First_Name");

        fName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String theFN = dataSnapshot.getValue(String.class);
                welcomeUser.setText("Welcome, " + theFN);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Takes you to the scan screen
        delivererBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setScanItemBtn(delivererBtn);
            }
        });

        //Takes you to the account info screen
        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAccountInfoBtn(customerBtn);
            }
        });

        //Option to logout
        toLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
    }


    /************/
    /*  ALERTS  */
    /************/
    private void logOut()
    {
        AlertDialog.Builder emailAlert = new AlertDialog.Builder(this);
        emailAlert.setMessage("Are you sure you want to continue?")
                .setNegativeButton("Opps, misclick", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes, log me out.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        myFirebaseRef.unauth();
                        Intent i = new Intent (homeScreen.this, MainActivity.class);
                        startActivity(i);
                    }
                })
                .setTitle("Log Out")
                .create();
        emailAlert.show();
    }


    /***********/
    /* INTENTS */
    /***********/

    // This intent is to take the user from Home Screen to Scan Item
    public void setScanItemBtn(View v)
    {
        if(v.getId() == R.id.deliverBtn)
        {
            Intent i = new Intent (homeScreen.this, deliveryTermsAndConditions.class);
            startActivity(i);
        }
    }

    // This intent is to take the user from Home Screen to Account Info
    public void setAccountInfoBtn(View v)
    {
        if(v.getId() == R.id.customerBtn)
        {
            Intent i = new Intent (homeScreen.this, customerTermsAndConditions.class);
            startActivity(i);
        }
    }

    // NEED TO CHANGE TO LOGOUT
    public void setLogOut(View v)
    {
        if(v.getId() == R.id.logOut)
        {
            Intent i = new Intent (homeScreen.this, MainActivity.class);
            startActivity(i);
        }
    }
}