package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListServiceForUsers extends AppCompatActivity {
    public static final String SERVICE_ID = "serviceId";

    ListView listViewServices;

    DatabaseReference databaseService;

    List<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service_for_users);

        databaseService = FirebaseDatabase.getInstance().getReference("service");
        listViewServices = (ListView) findViewById(R.id.listViewServices);

        services = new ArrayList<>();

        //attaching listener to listview
        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                Service service = services.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), ClientOrder.class);
                intent.putExtra(SERVICE_ID, service.getServiceId());

//                Log.v("E_VALUE1","Company_name:"+company.getCompanyName());
//                Log.v("E_VALUE1","CompanyId:"+company.getCompanyId());
                //starting the activity with intent
                startActivity(intent);
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
                ServiceList serviceListAdapter = new ServiceList(ListServiceForUsers.this, services);
                listViewServices.setAdapter(serviceListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
