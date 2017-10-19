package com.androidbash.androidbashfirebaseupdated.tabs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidbash.androidbashfirebaseupdated.Company;
import com.androidbash.androidbashfirebaseupdated.CompanyList;
import com.androidbash.androidbashfirebaseupdated.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class contentFragmentTab1 extends Fragment {

    ListView listViewCompany;
    List<Company> companies;
    SharedPreferences sPref;
    DatabaseReference databaseCompany;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.tab1,container,false);


//        sPref = getSharedPreferences("user_id",MODE_PRIVATE);
//        final String uid = sPref.getString("user_id","");


        databaseCompany = FirebaseDatabase.getInstance().getReference("company");
       View view = inflater.inflate(R.layout.tab1, container, false);
        listViewCompany = (ListView) view.findViewById(R.id.listViewCompany);
            companies = new ArrayList<>();
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();

        databaseCompany.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                companies.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Company company = postSnapshot.getValue(Company.class);
                    companies.add(company);
                }
                CompanyList companyListAdapter = new CompanyList(getActivity(), companies);
                listViewCompany.setAdapter(companyListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
