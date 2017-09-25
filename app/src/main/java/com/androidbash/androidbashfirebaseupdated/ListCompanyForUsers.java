package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.content.SharedPreferences;
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

//МОЖНА ВИДАЛИТИ


// Список компанії для показу користувачам
public class ListCompanyForUsers extends AppCompatActivity {
    public static final String TRACK_ID = "companyId";

    DatabaseReference databaseCompany;
    List<Company> companies = new ArrayList<>();

    private ListView listViewCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity:","Start ListCompanyForUsers");
        setContentView(R.layout.activity_list_company_for_users);
        databaseCompany = FirebaseDatabase.getInstance().getReference("company");

        initView();
        initListeners();

    }

    private void initView() {
        listViewCompany = (ListView) findViewById(R.id.listViewCompany);
    }

    private void initListeners() {
        //attaching listener to listview
        listViewCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Company company = companies.get(i);
                Intent intent = new Intent(getApplicationContext(), ListServiceForUsers.class);
                intent.putExtra(TRACK_ID, company.getCompanyId());

                Log.v("E_VALUE1", "CompanyId:" + company.getCompanyId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseCompany.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            companies.clear();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                Company company = postSnapshot.getValue(Company.class);
                companies.add(company);
            }
            CompanyList companyListAdapter = new CompanyList(ListCompanyForUsers.this, companies);
            listViewCompany.setAdapter(companyListAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
