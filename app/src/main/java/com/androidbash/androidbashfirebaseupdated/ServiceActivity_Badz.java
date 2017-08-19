package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ServiceActivity_Badz extends AppCompatActivity {

    TextView company_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);

        company_id = (TextView) findViewById(R.id.company_id);

        Intent intent = getIntent();
        String id = intent.getStringExtra("Company_id");
        Log.v("E_VALUE","Company_id:"+id);

        company_id.setText(intent.getStringExtra("Company_id"));
    }
}
