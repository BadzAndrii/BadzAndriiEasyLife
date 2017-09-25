package com.androidbash.androidbashfirebaseupdated.admin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbash.androidbashfirebaseupdated.R;

public class OrdersTableFragment extends Fragment {
    private View root;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start SignUpClientFragment");
        root = inflater.inflate(R.layout.activity_sign_up_client, container, false);

        initView();
        initListeners();



        return root;
    }

    private void initView() {

    }

    private void initListeners() {

    }
}
