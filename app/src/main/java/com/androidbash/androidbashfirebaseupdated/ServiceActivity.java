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

import com.androidbash.androidbashfirebaseupdated.utils.MyTextUtil;
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


//МОЖНА ВИДАЛИТИ



public class ServiceActivity extends AppCompatActivity {

    private Firebase myFirebaseRef;

    Button buttonAddService;
    EditText editTextServiceName;
    EditText editTextServiceDescription;
    EditText editTextServiceTime;
    ListView listViewServices;

    DatabaseReference databaseService;

    List<Service> services = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity:","Start ServiceActivity");
        setContentView(R.layout.activity_service);

        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/company/");
        databaseService = FirebaseDatabase.getInstance().getReference("service");

        initView();
        initListener();
    }

    private void initView() {
        buttonAddService = (Button) findViewById(R.id.buttonAddService);
        editTextServiceName = (EditText) findViewById(R.id.editTextServiceName);
        editTextServiceDescription = (EditText) findViewById(R.id.editTextServiceDescription);
        editTextServiceTime = (EditText) findViewById(R.id.editTextServiceTime);
        listViewServices = (ListView) findViewById(R.id.listViewServices);

    }

    private void initListener() {
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
        databaseService.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
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
    };

    private void saveService() {
        Intent intent = getIntent();

        String serviceName = MyTextUtil.getText(editTextServiceName);
        String serviceDescription = MyTextUtil.getText(editTextServiceDescription);
        String serviceTime = MyTextUtil.getText(editTextServiceTime);

        if (!TextUtils.isEmpty(serviceName) && !TextUtils.isEmpty(serviceDescription) && !TextUtils.isEmpty(serviceTime)) {
            String id = databaseService.push().getKey();

            Service service = new Service(id, serviceName, serviceDescription, serviceTime);
            databaseService.child(id).setValue(service);
            myFirebaseRef.child(intent.getStringExtra(CompanyActivity.TRACK_ID)).child("servicesId").child(id).setValue(id);
            Toast.makeText(this, "Послуга додана", Toast.LENGTH_LONG).show();
            editTextServiceName.setText("");
            editTextServiceDescription.setText("");
            editTextServiceTime.setText("");
        } else {
            Toast.makeText(this, "Будь ласка, заповніть усі дані про послугу", Toast.LENGTH_LONG).show();
        }
    }


}
