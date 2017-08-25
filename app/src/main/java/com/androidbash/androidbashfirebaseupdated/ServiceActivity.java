package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

//    by
    private Firebase myFirebaseRef;
//    private TextView welcomeText;
//

    Button buttonAddService;
    EditText editTextServiceName;
    EditText editTextServiceDescription;
    EditText editTextServiceTime;
    ListView listViewServices;

    DatabaseReference databaseService;

    List<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


//        by
        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/company/");
//
//        Intent intent = getIntent();


//        databaseService = FirebaseDatabase.getInstance().getReference("company").child(intent.getStringExtra(CompanyActivity.TRACK_ID));
        databaseService = FirebaseDatabase.getInstance().getReference("service");

        buttonAddService = (Button) findViewById(R.id.buttonAddService);
        editTextServiceName = (EditText) findViewById(R.id.editTextServiceName);
        editTextServiceDescription = (EditText) findViewById(R.id.editTextServiceDescription);
        editTextServiceTime = (EditText) findViewById(R.id.editTextServiceTime);
        listViewServices = (ListView) findViewById(R.id.listViewServices);

        services = new ArrayList<>();

        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveService();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ServiceList serviceListAdapter = new ServiceList(ServiceActivity.this, services);
                listViewServices.setAdapter(serviceListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void saveService() {
//        by
        Intent intent = getIntent();
//

        String serviceName = editTextServiceName.getText().toString().trim();
        String serviceDescription = editTextServiceDescription.getText().toString().trim();
        String serviceTime = editTextServiceTime.getText().toString().trim();

        if (!TextUtils.isEmpty(serviceName)&&!TextUtils.isEmpty(serviceDescription)&&!TextUtils.isEmpty(serviceTime)) {
            String id  = databaseService.push().getKey();

            Service service = new Service(id,serviceName,serviceDescription,serviceTime);
            databaseService.child(id).setValue(service);

//            by

            myFirebaseRef.child(intent.getStringExtra(CompanyActivity.TRACK_ID)).child("servicesId").child(id).setValue(id);

//
            Toast.makeText(this, "Послуга додана", Toast.LENGTH_LONG).show();
            editTextServiceName.setText("");
            editTextServiceDescription.setText("");
            editTextServiceTime.setText("");
        }
            else {
            Toast.makeText(this, "Будь ласка, заповніть усі дані про послугу", Toast.LENGTH_LONG).show();
        }
    }


}
