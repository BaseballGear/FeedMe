package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by BaseballGear on 5/3/2016.
 */
public class customerRequest extends Activity
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
        String [] customers = {"Nick - Taco Bell", "Robert - Burger King", "Jake - Taco Bell", "Sally - Dairy Queen","Tony - Mcdonald's", "Barb - Wendy's"};

       /* ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, customers);

        ListView theView = (ListView) findViewById(R.id.theCusOrders);

        theView.setAdapter(theAdapter);

        theView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCustomer();
            }
        });

                System.out.println(customers);
                */

        createTheOrder = (Button)findViewById(R.id.acceptCus);
        createTheOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMakePayment(createTheOrder);
            }
        });

    }

    private void selectedCustomer()
    {
        AlertDialog.Builder emailAlert = new AlertDialog.Builder(this);
        emailAlert.setMessage("Are you sure you want this customer?")
                .setPositiveButton("Yes, I am sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        Intent i = new Intent (customerRequest.this, currentCustomers.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("No, take me back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent i = new Intent(customerRequest.this, customerRequest.class);
                        startActivity(i);
                    }
                })
                .setTitle("Customer Selected")
                .create();
        emailAlert.show();
    }

public void setMakePayment(View v)
    {
        if(v.getId() == R.id.acceptCus)
        {
            Intent i = new Intent(customerRequest.this, currentCustomers.class);
            startActivity(i);
        }
    }

}