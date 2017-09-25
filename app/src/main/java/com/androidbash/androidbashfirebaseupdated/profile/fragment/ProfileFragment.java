package com.androidbash.androidbashfirebaseupdated.profile.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidbash.androidbashfirebaseupdated.R;

public class ProfileFragment extends Fragment {
    private View root;
    private TextView ProfileName;
    private TextView ProfilePhoneNumber;
    private TextView ProfileEmail;


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start ProfileFragment");
        root = inflater.inflate(R.layout.activity_profile, container, false);

        initView();
        initListeners();

        String strtext = getArguments().getString("name");
        ProfileName.setText(strtext);
        ProfilePhoneNumber.setText(strtext);
        ProfileEmail.setText(strtext);
        return root;
    }


    private void initView() {
        ProfileName = (TextView) root.findViewById(R.id.profile_name);
        ProfileEmail = (TextView) root.findViewById(R.id.profile_email);
        ProfilePhoneNumber = (TextView) root.findViewById(R.id.profile_phone);

    }
    private void initListeners() {

    }

}
