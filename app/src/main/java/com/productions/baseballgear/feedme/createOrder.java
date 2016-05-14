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
 * Created by BaseballGear on 5/9/2016.
 */
public class createOrder extends Activity
{
    Firebase myFirebaseRef = new Firebase("https://feedmefood.firebaseio.com/");
    String uid = myFirebaseRef.getAuth().getUid();
    TextView restName;
    TextView orderDetails;
    TextView specialInstructions;
    Button submitOrder;
    Boolean noOrder = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);
    }
    @Override
    protected void onStart() {
        super.onStart();

        restName = (TextView) findViewById(R.id.restName);
        orderDetails = (TextView) findViewById(R.id.theOrder);
        specialInstructions = (TextView) findViewById(R.id.specialInstructions);
        submitOrder = (Button) findViewById(R.id.createOrder);

        while(noOrder)
        {

            submitOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myFirebaseRef.child("Customer").child(uid).child("Restaurants_Name").setValue(restName.getText().toString());
                    myFirebaseRef.child("Customer").child(uid).child("The_Order_Details").setValue(orderDetails.getText().toString());
                    myFirebaseRef.child("Customer").child(uid).child("Special_Instructions").setValue(specialInstructions.getText().toString());

                    if (!restName.getText().toString().equals(null) && !orderDetails.getText().toString().equals(null)) {
                        noOrder= true;
                        sendOrder(submitOrder);
                    }

                }
            });
        }
    }

    public void sendOrder (View v)
    {
        if(v.getId() == R.id.createOrder)
        {
            Intent i = new Intent (createOrder.this, showCurrentOrders.class);
            startActivity(i);
        }
    }

}
