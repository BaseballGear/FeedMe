package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by BaseballGear on 5/9/2016.
 */
public class showCurrentOrders extends Activity
{
    Firebase myFirebaseRef = new Firebase("https://feedmefood.firebaseio.com/");
    String uid = myFirebaseRef.getAuth().getUid();

    TextView restName;
    TextView theOrder;
    TextView theSpecials;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);
    }
    @Override
    protected void onStart()
    {
        super.onStart();

        restName = (TextView)findViewById(R.id.restaurantName);
        theOrder = (TextView)findViewById(R.id.theOrderDetails);
        theSpecials = (TextView)findViewById(R.id.theSpecialInstructions);

        final Firebase restaurantName = myFirebaseRef.child("Customer").child(uid).child("Restaurant_Name");
        final Firebase orderDetails = myFirebaseRef.child("Customer").child(uid).child("The_Order_Details");
        final Firebase specials = myFirebaseRef.child("Customer").child(uid).child("Special_Instructions");

        restName.setText("Restaurant: ");

        restaurantName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String theRN = dataSnapshot.getValue(String.class);
                restName.setText("Restaurant: " + theRN);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        orderDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String theIO = dataSnapshot.getValue(String.class);
                theOrder.setText("Items Ordered: " + theIO);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        specials.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String theSI = dataSnapshot.getValue(String.class);
                theSpecials.setText("Special Instructions: " + theSI);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
