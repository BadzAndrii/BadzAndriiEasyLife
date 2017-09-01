package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.utils.MyTextUtil;
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

public class CompanyActivity extends AppCompatActivity {
    public static final String TRACK_ID = "companyId";

    Button buttonAddCompany;
    EditText editTextCompanyName;
    EditText editTextCompanyDescription;

    TextView textViewArtist;
    ListView listViewCompany;

    DatabaseReference databaseCompany;

    List<Company> companies = new ArrayList<>();

    SharedPreferences sPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Activity:","Start CompanyActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        sPref = getSharedPreferences("user_id", MODE_PRIVATE);
//        final String uid = sPref.getString("user_id","");


        databaseCompany = FirebaseDatabase.getInstance().getReference("company");
//        databaseCompany = FirebaseDatabase.getInstance().getReference("company").child(uid);

        initView();
        initListeners();
//        companies = new ArrayList<>();


    }

    private void initView() {
        buttonAddCompany = (Button) findViewById(R.id.buttonAddCompany);
        editTextCompanyName = (EditText) findViewById(R.id.editTextName);
        editTextCompanyDescription = (EditText) findViewById(R.id.editTextDescription);
        textViewArtist = (TextView) findViewById(R.id.textViewArtist);
        listViewCompany = (ListView) findViewById(R.id.listViewCompany);
    }

    private void initListeners() {
        buttonAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCompany();
            }
        });

        //attaching listener to listview
        listViewCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                Company company = companies.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                intent.putExtra(TRACK_ID, company.getCompanyId());

//                Log.v("E_VALUE1","Company_name:"+company.getCompanyName());
                Log.v("E_VALUE1", "companyId:" + company.getCompanyId());
                //starting the activity with intent
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
                final String uid = sPref.getString("user_id", "");
                Company company = postSnapshot.getValue(Company.class);

                String userCompanyId = company.getUserId();
                Log.v("E_VALUE1", "userId:" + userCompanyId);
                Log.v("E_VALUE1", "uid:" + uid);
                if(userCompanyId.equals(uid)) {
                    companies.add(company);
                }
            }
            CompanyList companyListAdapter = new CompanyList(CompanyActivity.this, companies);
            listViewCompany.setAdapter(companyListAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void saveCompany() {
        //test
        String companyName = MyTextUtil.getText(editTextCompanyName);
//        String companyName = editTextCompanyName.getText().toString().trim();

        String companyDescription = MyTextUtil.getText(editTextCompanyDescription);
//        String companyDescription = editTextCompanyDescription.getText().toString().trim();
        final String uid = sPref.getString("user_id", "");

        if (!TextUtils.isEmpty(companyName)) {
            String id = databaseCompany.push().getKey();

            Company company = new Company(id, companyName, companyDescription, uid);

            databaseCompany.child(id).setValue(company);

            Toast.makeText(this, "Company saved", Toast.LENGTH_LONG).show();
            editTextCompanyName.setText("");
            editTextCompanyDescription.setText("");
        } else {
            Toast.makeText(this, "Please enter company name", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        databaseCompany.removeEventListener(valueEventListener);
        super.onStop();
    }
}
