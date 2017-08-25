package com.androidbash.androidbashfirebaseupdated;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ListCompanyForUsers extends AppCompatActivity {

    DatabaseReference databaseCompany;
    List<Company> companies;

    private ListView listViewCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_company_for_users);
        databaseCompany = FirebaseDatabase.getInstance().getReference("company");

        listViewCompany = (ListView) findViewById(R.id.listViewCompany);

        companies = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseCompany.addValueEventListener(new ValueEventListener() {
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
        });
    }

}
