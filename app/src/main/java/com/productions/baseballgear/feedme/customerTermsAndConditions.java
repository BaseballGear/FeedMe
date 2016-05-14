package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by BaseballGear on 5/9/2016.
 */
public class customerTermsAndConditions extends Activity
{
    CheckBox didAccept;
    Button continueToOrder;
    Boolean notAccepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_terms_and_conditions);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        didAccept = (CheckBox) findViewById(R.id.acceptBox);
        continueToOrder = (Button) findViewById(R.id.toOrderButton);
        continueToOrder.setEnabled(false);

        didAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setDidAccept(didAccept);
            }
        });

        continueToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setContinueToOrder(continueToOrder);
            }
        });
    }

    public void setDidAccept(View v)
    {
        continueToOrder = (Button) findViewById(R.id.toOrderButton);

        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked())
        {
            continueToOrder.setEnabled(true);
        }
        else
        {
            continueToOrder.setEnabled(false);
        }
    }

    public void setContinueToOrder(View v)
    {
        if(v.getId() == R.id.toOrderButton)
        {
            Intent i = new Intent (customerTermsAndConditions.this, securityChargeCustomer.class);
            startActivity(i);
        }
    }
}
