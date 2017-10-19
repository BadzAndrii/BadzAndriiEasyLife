package com.androidbash.androidbashfirebaseupdated.admin.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.Company;
import com.androidbash.androidbashfirebaseupdated.CompanyList;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.Service;
import com.androidbash.androidbashfirebaseupdated.ServiceList;
import com.androidbash.androidbashfirebaseupdated.admin.fragment.RegisterCompanyFragment;
import com.androidbash.androidbashfirebaseupdated.admin.fragment.ServiceTableFragment;
import com.firebase.client.Firebase;
import com.firebase.client.core.Context;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    String fileName;

    public static final String TRACK_ID = "companyId";
    DatabaseReference databaseCompany;
    ListView listViewCompany;
    ListView listViewServices;
    Fragment fragment;
    private Firebase myFirebaseRef;
    List<Company> companies = new ArrayList<>();
    List<Service> services = new ArrayList<>();
    SharedPreferences sPref;
    SharedPreferences companyId;


    DatabaseReference databaseService;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("Activity:", "Start ADMINACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercompanyactivity);
        sPref = getSharedPreferences("user_id", MODE_PRIVATE);
        companyId = getSharedPreferences("companyId", MODE_PRIVATE);
//        final String uid = sPref.getString("user_id","");

        databaseCompany = FirebaseDatabase.getInstance().getReference("company");
        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/company/");
        databaseService = FirebaseDatabase.getInstance().getReference("service");

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

    public void showRegisterCompany() {
        replaceFragment(new RegisterCompanyFragment());
    }
    public void showServiceTableFragment() {
        replaceFragment(new ServiceTableFragment());
    }

    public void addCompanyBtn(String companyName, String companyDescription, String uid) {

        String id = databaseCompany.push().getKey();

        Company company = new Company(id, companyName, companyDescription, uid);

        databaseCompany.child(id).setValue(company);

    }

    @Override
    protected void onStart() {

        super.onStart();
        showRegisterCompany();

    }

    public void nextService(int i) {
        Company company = companies.get(i);
//        Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
//              intent.putExtra(TRACK_ID, company.getCompanyId());
//                Log.v("E_VALUE1","Company_name:"+company.getCompanyName());
        Log.v("E_VALUE1", "companyId:" + company.getCompanyId());

        companyId = getSharedPreferences("companyId", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("companyId", company.getCompanyId());
        ed.commit();


        //PACK DATA IN A BUNDLE
        Bundle bundle = new Bundle();
        bundle.putString("companyId", company.getCompanyId());
        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        ServiceTableFragment myFragment = new ServiceTableFragment();
        myFragment.setArguments(bundle);
        //THEN NOW SHOW OUR FRAGMENT
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,myFragment).commit();

        //                    END TEST
        //starting the activity with intent
//        startActivity(intent);
//       showServiceTableFragment();
    }
    public void listCompany() {
        databaseCompany.addValueEventListener(valueEventListener);
        listViewCompany = (ListView) findViewById(R.id.listViewCompany);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            companies.clear();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                final String uid = sPref.getString("user_id", "");

                Company company = postSnapshot.getValue(Company.class);

                String userCompanyId = company.getUserId();
                Log.v("E_VALUE1", "userId:" + userCompanyId);
//                Log.v("E_VALUE1", "uid:" + uid);
                if(userCompanyId.equals(uid)) {
                    companies.add(company);
                }
            }

            CompanyList companyListAdapter = new CompanyList(AdminActivity.this, companies);

            listViewCompany.setAdapter(companyListAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    public void listServices() {
        databaseCompany.addValueEventListener(valueEventListener);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        databaseService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ServiceList serviceListAdapter = new ServiceList(AdminActivity.this, services);
                listViewServices.setAdapter(serviceListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    @Override
    public void onStop() {
//        databaseCompany.removeEventListener(valueEventListener);
        super.onStop();
    }
    public void createNewService(String serviceName , String serviceDescription, String serviceTime)
    {
        Log.v("E_VALUE1", "START:");
//        final String compId = companyId.getString("companyId", "");
        final String compId = getFileName();

        Log.v("E_VALUE1", "compId:"+ compId);
         if (!TextUtils.isEmpty(serviceName) && !TextUtils.isEmpty(serviceDescription) && !TextUtils.isEmpty(serviceTime)) {
            String id = databaseService.push().getKey();

            Service service = new Service(id, serviceName, serviceDescription, serviceTime);
            databaseService.child(id).setValue(service);
            myFirebaseRef.child(compId).child("servicesId").child(id).setValue(id);
            Toast.makeText(this, "Послуга додана", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Будь ласка, заповніть усі дані про послугу", Toast.LENGTH_LONG).show();
        }
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



    public String getFileName() {
        return fileName;
    }
    public String setFileName(String fileName) {
        this.fileName = fileName;
        return fileName;
    }
}
