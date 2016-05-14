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
public class deliveryTermsAndConditions extends Activity
{
    CheckBox didAccept;
    Button startDelivering;

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
        startDelivering = (Button) findViewById(R.id.toOrderButton);
        startDelivering.setEnabled(false);

        didAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setDidAccept(didAccept);
            }
        });

        startDelivering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setContinueToOrder(startDelivering);
            }
        });
    }

    public void setDidAccept(View v)
    {
        startDelivering = (Button) findViewById(R.id.toOrderButton);

        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked())
        {
            startDelivering.setEnabled(true);
        }
        else
        {
            startDelivering.setEnabled(false);
        }
    }

    public void setContinueToOrder(View v)
    {
        if(v.getId() == R.id.toOrderButton)
        {
            Intent i = new Intent (deliveryTermsAndConditions.this, securityChargeDeliverer.class);
            startActivity(i);
        }
    }
}
