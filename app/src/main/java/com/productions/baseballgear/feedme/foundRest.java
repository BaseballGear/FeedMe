package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by BaseballGear on 5/3/2016.
 */
public class foundRest extends Activity
{
    Button createTheOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.found_rest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        String [] restaurants = {"Burger King", "Culver's", "Taco Bell", "Dairy Queen","Mcdonald's", "Wendy's"};

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restaurants);

        ListView theView = (ListView) findViewById(R.id.theRestaurants);

        theView.setAdapter(theAdapter);

        System.out.println(restaurants);

        createTheOrder = (Button)findViewById(R.id.createOrder);

        createTheOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setMakePayment(createTheOrder);
            }
        });
    }

    public void setMakePayment(View v)
    {
        if(v.getId() == R.id.createOrder)
        {
            Intent i = new Intent(foundRest.this, createOrder.class);
            startActivity(i);
        }
    }
}