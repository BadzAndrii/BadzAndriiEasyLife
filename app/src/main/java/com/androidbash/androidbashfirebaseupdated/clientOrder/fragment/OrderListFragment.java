package com.androidbash.androidbashfirebaseupdated.clientOrder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.Service;
import com.androidbash.androidbashfirebaseupdated.client.activity.ClientActivity;
import com.androidbash.androidbashfirebaseupdated.clientOrder.activity.ClientActivityOrder;

import java.util.ArrayList;

public class OrderListFragment extends Fragment {
    private View root;
    ListView listViewOrders;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start OrderListFragment");
        root = inflater.inflate(R.layout.order_activity, container, false);

        initView();
        return root;
    }

    private void initView() {
        listViewOrders = (ListView) root.findViewById(R.id.listViewOrders);
    }


    @Override
    public void onStart() {
        super.onStart();
        ((ClientActivityOrder) getActivity()).listOrders();
//        ((ClientActivityOrder) getActivity()).initFirebase();

    }

}
