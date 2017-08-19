package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {


    Button buttonAddTrack;
    EditText editTextTrackName;
    EditText editTextTrackDescription;
    ListView listViewServices;

    DatabaseReference databaseService;

    List<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Intent intent = getIntent();

        /*
        * this line is important
        * this time we are not getting the reference of a direct node
        * but inside the node track we are creating a new child with the artist id
        * and inside that node we will store all the tracks with unique ids
        * */
        databaseService = FirebaseDatabase.getInstance().getReference("service").child(intent.getStringExtra(CompanyActivity.TRACK_ID));

        buttonAddTrack = (Button) findViewById(R.id.buttonAddTrack);
        editTextTrackName = (EditText) findViewById(R.id.editTextServiceName);
        editTextTrackDescription = (EditText) findViewById(R.id.editTextServiceDescription);
        listViewServices = (ListView) findViewById(R.id.listViewServices);

        services = new ArrayList<>();

        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTrack();
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

    private void saveTrack() {
        String serviceName = editTextTrackName.getText().toString().trim();
        String serviceDescription = editTextTrackDescription.getText().toString().trim();

        if (!TextUtils.isEmpty(serviceName)) {
            String id  = databaseService.push().getKey();

            Service service = new Service(serviceName,serviceDescription);
            databaseService.child(id).setValue(service);
            Toast.makeText(this, "Track saved", Toast.LENGTH_LONG).show();
            editTextTrackName.setText("");
            editTextTrackDescription.setText("");
        }
            else {
            Toast.makeText(this, "Please enter track name", Toast.LENGTH_LONG).show();
        }
    }
}
