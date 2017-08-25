package com.androidbash.androidbashfirebaseupdated;

import android.app.Application;

import com.firebase.client.Firebase;


public class NewCompany extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}

