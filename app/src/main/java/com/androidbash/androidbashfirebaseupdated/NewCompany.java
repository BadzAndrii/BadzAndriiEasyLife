package com.androidbash.androidbashfirebaseupdated;

import android.app.Application;
import android.util.Log;

import com.firebase.client.Firebase;


public class NewCompany extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Activity:","Start NewCompany");
        Firebase.setAndroidContext(this);
    }
}

