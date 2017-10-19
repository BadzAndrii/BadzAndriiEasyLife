package com.androidbash.androidbashfirebaseupdated.client.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.BoxAdapter;
import com.androidbash.androidbashfirebaseupdated.ListServiceForUsers;
import com.androidbash.androidbashfirebaseupdated.Order;
import com.androidbash.androidbashfirebaseupdated.Product;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.Service;
import com.androidbash.androidbashfirebaseupdated.admin.activity.AdminActivity;
import com.androidbash.androidbashfirebaseupdated.client.activity.ClientActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RegisterOrderFragment extends Fragment {
    private View root;
    private String serviceIdFromFragment;
    private String serviceNameFromFragment;
    Button buttonAddOrder;
    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start RegisterOrderFragment");
        root = inflater.inflate(R.layout.activity_client_order, container, false);

        initView();
        initListeners();

        //UNPACK OUR DATA FROM OUR BUNDLE
        String serviceIdFromClientActivity = this.getArguments().getString("serviceId").toString();
        serviceIdFromFragment = ((ClientActivity) getActivity()).setServiceId(serviceIdFromClientActivity);
        Log.v("Activity:", "serviceIdFromClientActivity"+serviceIdFromClientActivity);

        //TEST for ORDER CLIENT
        String serviceNameFrom = this.getArguments().getString("serviceName").toString();
        serviceNameFromFragment = ((ClientActivity) getActivity()).setServiceNameForOrder(serviceNameFrom);
        Log.v("Activity:", "serviceNameFrom: "+serviceNameFrom);

//        boxAdapter = new BoxAdapter(getActivity(), products);
//        // настраиваем список
//        ListView lvMain = (ListView) root.findViewById(R.id.lvMain);
//        lvMain.setAdapter(boxAdapter);

        return root;
    }

    private void initView() {
        buttonAddOrder = (Button) root.findViewById(R.id.buttonAddOrder);
    }

    private void initListeners() {
        //attaching listener to listview
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClientActivity) getActivity()).saveOrder();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((ClientActivity) getActivity()).fillData();
    }

}
