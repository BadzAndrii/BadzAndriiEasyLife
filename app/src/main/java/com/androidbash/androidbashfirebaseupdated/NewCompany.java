package com.androidbash.androidbashfirebaseupdated;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by andri on 19.08.2017.
 */

public class NewCompany extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}

