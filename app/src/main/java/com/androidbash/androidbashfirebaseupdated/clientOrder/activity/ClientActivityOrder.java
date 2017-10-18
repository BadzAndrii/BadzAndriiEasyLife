package com.androidbash.androidbashfirebaseupdated.clientOrder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.BoxAdapter;
import com.androidbash.androidbashfirebaseupdated.Company;
import com.androidbash.androidbashfirebaseupdated.CompanyList;
import com.androidbash.androidbashfirebaseupdated.Order;
import com.androidbash.androidbashfirebaseupdated.OrdersClientList;
import com.androidbash.androidbashfirebaseupdated.Product;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.Service;
import com.androidbash.androidbashfirebaseupdated.ServiceList;
import com.androidbash.androidbashfirebaseupdated.admin.activity.AdminActivity;
import com.androidbash.androidbashfirebaseupdated.clientOrder.fragment.OrderListFragment;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ClientActivityOrder extends AppCompatActivity {
    private Firebase myFirebaseRef;
    private FirebaseAuth mAuth;
    SharedPreferences sPref;
    DatabaseReference databaseServices;

    ListView listViewOrders;
    List<Order> orders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("Activity:", "Start ClientActivityOrder");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcompanyactivity);
        sPref = getSharedPreferences("user_id", MODE_PRIVATE);
        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/orders/");
        mAuth = FirebaseAuth.getInstance();

        databaseServices = FirebaseDatabase.getInstance().getReference("orders");

//        knopka BACK
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    public void showServiceList() {
        replaceFragment(new OrderListFragment());
    }

//    public void initFirebase() {
//        sPref = getSharedPreferences("user_id", MODE_PRIVATE);
//        final String uid = sPref.getString("user_id", "");
//
//        myFirebaseRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
//            @Override
//            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
//                Map<String, String> map = dataSnapshot.getValue(Map.class);
//
//                String companyId = map.get("companyId");
//                String orderId = map.get("orderId");
//                String serviceId = map.get("serviceId");
//                String startTime = map.get("startTime");
//                String userId = map.get("userId");
//
//
//                Log.v("E_VALUE", "companyId:" + companyId);
//                Log.v("E_VALUE", "orderId:" + orderId);
//                Log.v("E_VALUE", "serviceId:" + serviceId);
//                Log.v("E_VALUE", "startTime:" + startTime);
//                Log.v("E_VALUE", "userId:" + userId);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//    }


    public void listOrders() {
        final String uid = sPref.getString("user_id", "");
        Log.v("Activity:", "uid"+uid);
        listViewOrders = (ListView) findViewById(R.id.listViewOrders);
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orders.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Order order = postSnapshot.getValue(Order.class);

                    String orderId = order.getUserId();
                    Log.v("E_VALUE1", "orderId:" + orderId);
                    if(orderId.equals(uid)) {
                        orders.add(order);
                    }

                }
                OrdersClientList serviceListAdapter = new OrdersClientList(ClientActivityOrder.this, orders);
                listViewOrders.setAdapter(serviceListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();
        showServiceList();
    }


//    for KNOPKA back in Action
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            finish();
        }
        return true;
    }

}
