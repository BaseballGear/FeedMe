package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

/**
 * Created by BaseballGear on 4/19/2016.
 */
public class userInfo extends Activity
{
    TextView firstNameTxt;
    TextView lastNameTxt;
    TextView phoneNumberTxt;
    TextView payPalEmailTxt;
    Button subUserInfoBtn;
    TextView toPPweb;

    Firebase myFirebaseRef = new Firebase("https://feedmefood.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        firstNameTxt = (TextView) findViewById(R.id.firstName);
        lastNameTxt = (TextView) findViewById(R.id.lastName);
        phoneNumberTxt = (TextView) findViewById(R.id.userPhone);
        payPalEmailTxt = (TextView) findViewById(R.id.userPayPal);
        subUserInfoBtn = (Button) findViewById(R.id.submitUserInfoBtn);
        toPPweb = (TextView) findViewById(R.id.linkToPP);

        Bundle theUID = getIntent().getExtras();
        if (theUID != null)
        {
            final String currentUID = theUID.getString("theUID");
            final String currentUserEmail = theUID.getString("theUserEmail");

            subUserInfoBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    myFirebaseRef.child("User Accounts").child(currentUID).child("First_Name").setValue(firstNameTxt.getText().toString());
                    myFirebaseRef.child("User Accounts").child(currentUID).child("Last_Name").setValue(lastNameTxt.getText().toString());
                    myFirebaseRef.child("User Accounts").child(currentUID).child("Phone_Number").setValue(phoneNumberTxt.getText().toString());
                    myFirebaseRef.child("User Accounts").child(currentUID).child("PayPal_Email").setValue(payPalEmailTxt.getText().toString());
                    myFirebaseRef.child("User Accounts").child(currentUID).child("User_Email").setValue(currentUserEmail);

                    System.out.println("We hit firebase");

                    if(!firstNameTxt.getText().toString().equals(null) && !lastNameTxt.getText().toString().equals(null) &&
                            !phoneNumberTxt.getText().toString().equals(null) && !payPalEmailTxt.getText().toString().equals(null))
                    {
                        submitUserInfo(subUserInfoBtn);
                    }
                    else
                    {
                        // Display error message.
                    }

                }
            });
        }

        //When click it takes you to PayPal sign up page.
        toPPweb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                takeToPayPalWeb(toPPweb);
            }
        });
    }

    //This gets called when user is done submitting info for account. Takes you back to the login screen.
    public void submitUserInfo(View v)
    {
        if(v.getId() == R.id.submitUserInfoBtn)
        {
            Intent subForm = new Intent (userInfo.this, MainActivity.class);
            startActivity(subForm);
        }
    }

    //This will get called when a user clicks to sign up for a PayPal account.
    public void takeToPayPalWeb (View v)
    {
        if(v.getId() == R.id.linkToPP)
        {
            Intent toPayPalWeb = new Intent (Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/signup"));
            startActivity(toPayPalWeb);
        }
    }



}
