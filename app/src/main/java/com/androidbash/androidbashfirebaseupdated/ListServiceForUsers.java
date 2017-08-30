package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListServiceForUsers extends AppCompatActivity {
    public static final String SERVICE_ID = "serviceId";
    public static final String TRACK_ID = "companyId";

    ListView listViewServices;

    DatabaseReference databaseService;

//    List<Service> services;
//for all services
private ArrayList<Service> services = new ArrayList<>();

    //        byyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
    private Firebase mRef;
    public ArrayList<String> mServices = new ArrayList<>();
    public ListView ListViewServices2;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service_for_users);

        databaseService = FirebaseDatabase.getInstance().getReference("service");
        listViewServices = (ListView) findViewById(R.id.listViewServices);

//        services = new ArrayList<>();

        //attaching listener to listview
        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
//                Service service = services.get(i);
                Service service = services.get(i);

                String company_id = getIntent().getStringExtra(ListCompanyForUsers.TRACK_ID);
                Log.v("TRACK_ID","TRACK_ID:"+company_id);


                //creating an intent
                Intent intent = new Intent(getApplicationContext(), ClientOrder.class);
                intent.putExtra(SERVICE_ID, service.getServiceId());
                intent.putExtra(TRACK_ID, company_id);

//                Log.v("E_VALUE1","Company_name:"+company.getCompanyName());
//                Log.v("E_VALUE1","CompanyId:"+company.getCompanyId());
                //starting the activity with intent
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        //        byyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
        Intent intent = getIntent();
        mRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/company/");
        ListViewServices2 = (ListView) findViewById(R.id.listViewServices2);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mServices);
        ListViewServices2.setAdapter(arrayAdapter);
        mRef.child(intent.getStringExtra(ListCompanyForUsers.TRACK_ID)).child("servicesId").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                mServices.add(value);
                arrayAdapter.notifyDataSetChanged();

                int size2 = mServices.size();
                Log.v("E_VALUE1","size2:"+size2);

                for( int i =0 ; i < size2; i++)
                {
                    String item = mServices.get(i);
                    Log.v("E_VALUE1",+i+"-item:"+item);

                    if(mServices.get(i)==item)
                    {
                        mServices.remove(item);
                    }


//
                    databaseService.orderByChild("serviceId").equalTo(item).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            services.clear();
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Log.v("E_VALUE1","dataSnapshot:"+dataSnapshot);
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
//

                }

            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
//



    }

}
