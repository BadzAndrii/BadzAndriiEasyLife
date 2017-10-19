package com.androidbash.androidbashfirebaseupdated.client.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidbash.androidbashfirebaseupdated.ClientOrder;
import com.androidbash.androidbashfirebaseupdated.ListCompanyForUsers;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.Service;
import com.androidbash.androidbashfirebaseupdated.admin.activity.AdminActivity;
import com.androidbash.androidbashfirebaseupdated.client.activity.ClientActivity;
import com.firebase.client.Firebase;

import java.util.ArrayList;

public class ServiceListFragment extends Fragment {
    private View root;
    private ArrayList<Service> services = new ArrayList<>();
    public ArrayList<String> mServices = new ArrayList<>();
    public ListView listViewServices;
    public ListView ListViewServices2;

    private String fileName;
    private String companyNameFor;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start ServiceListFragment");
        root = inflater.inflate(R.layout.activity_list_service_for_users, container, false);

        initView();
        initListeners();
        //UNPACK OUR DATA FROM OUR BUNDLE
        String companyIdFromClientActivity = this.getArguments().getString("companyId").toString();
        fileName = ((ClientActivity) getActivity()).setFileName(companyIdFromClientActivity);
        Log.v("Activity:", "CompanyId"+companyIdFromClientActivity);

//        TEST FOR ORDER
        String companyNameFromClientActivity = this.getArguments().getString("companyName").toString();
        companyNameFor = ((ClientActivity) getActivity()).setCompanyNameForOrder(companyNameFromClientActivity);
        Log.v("Activity:", "CompanyName:"+companyNameFromClientActivity);
//        END TEST
        return root;
    }

    private void initView() {
        listViewServices = (ListView) root.findViewById(R.id.listViewServices);
        ListViewServices2 = (ListView) root.findViewById(R.id.listViewServices2);
    }

    private void initListeners() {
        //attaching listener to listview
        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                ((ClientActivity) getActivity()).showOrderList(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((ClientActivity) getActivity()).showListServicesForUsersFromActivity();

    }

}
