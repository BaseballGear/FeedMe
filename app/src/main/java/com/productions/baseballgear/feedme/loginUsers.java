package com.productions.baseballgear.feedme;

import com.firebase.client.Firebase;

public class loginUsers extends android.app.Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}