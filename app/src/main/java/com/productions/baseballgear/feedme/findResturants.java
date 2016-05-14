package com.productions.baseballgear.feedme;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by BaseballGear on 5/9/2016.
 */
public class findResturants extends Activity
{
    Button findResturants;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_resturants);
    }

    @Override
    protected void onStart()
    {
        findResturants = (Button) findViewById(R.id.findRest);
        super.onStart();

        findingRest(findResturants);
    }

    public void findingRest (View v)
    {
        if(v.getId() == R.id.findRest)
        {
            Intent i = new Intent (findResturants.this, foundRest.class);
            startActivity(i);
        }
    }
}
