package com.androidbash.androidbashfirebaseupdated.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.MainActivity;
import com.androidbash.androidbashfirebaseupdated.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class ProfileFragment extends Fragment {

    private Firebase myFirebaseRef;
    private FirebaseAuth mAuth;

    private TextView ProfileName;

    private TextView ProfileCompany_name;

    private TextView ProfilePhoneNumber;

    private TextView ProfileCompany_description;

    private TextView ProfileEmail;

    private TextView text_view_company_name;

    private TextView text_view_company_description;


    SharedPreferences sPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container,
                false);
        getActivity().setTitle("Профіль");

        ProfileCompany_description = (TextView) rootView.findViewById(R.id.profile_company_description);
        ProfileCompany_name = (TextView) rootView.findViewById(R.id.profile_company_name);
        ProfileName = (TextView) rootView.findViewById(R.id.profile_name);
        ProfileEmail = (TextView) rootView.findViewById(R.id.profile_email);
        ProfilePhoneNumber = (TextView) rootView.findViewById(R.id.profile_phone);


        text_view_company_name = (TextView) rootView.findViewById(R.id.text_view_company_name);
        text_view_company_description = (TextView) rootView.findViewById(R.id.text_view_company_description);

        //Creates a reference for  your Firebase database
        //Add YOUR Firebase Reference URL instead of the following URL
        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/users/");
        mAuth = FirebaseAuth.getInstance();

//        Badz codding
        sPref = this.getActivity().getSharedPreferences("user_id", Context.MODE_PRIVATE);
        final String uid = sPref.getString("user_id","");
        myFirebaseRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> map = dataSnapshot.getValue(Map.class);

                String company_description = map.get("company_description");
                String company_name = map.get("company_name");
                String email = map.get("email");
                String id = map.get("id");
                String name = map.get("name");
                String password = map.get("password");
                String phoneNumber = map.get("phoneNumber");

                ProfileCompany_description.setText(company_description);
                ProfileCompany_name.setText(company_name);
                ProfileName.setText(name);
                ProfileEmail.setText(email);
                ProfilePhoneNumber.setText(phoneNumber);


                if (ProfileCompany_description.getText().toString().equals(""))
                {
                    ProfileCompany_description.setVisibility(View.GONE);
                    text_view_company_name.setVisibility(View.GONE);
                }
                else
                {
                    ProfileCompany_description.setVisibility(View.VISIBLE);
                    text_view_company_name.setVisibility(View.VISIBLE);
                }

                if (ProfileCompany_name.getText().toString().equals(""))
                {
                    ProfileCompany_name.setVisibility(View.GONE);
                    text_view_company_description.setVisibility(View.GONE);
                }
                else
                {
                    ProfileCompany_name.setVisibility(View.VISIBLE);
                    text_view_company_description.setVisibility(View.VISIBLE);
                }
                Log.v("E_VALUE","company_description:"+company_description);
                Log.v("E_VALUE","company_name:"+company_name);
                Log.v("E_VALUE","email:"+email);
                Log.v("E_VALUE","id:"+id);
                Log.v("E_VALUE","name:"+name);
                Log.v("E_VALUE","password:"+password);
                Log.v("E_VALUE","phoneNumber:"+phoneNumber);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//
        return rootView;
    }
}
