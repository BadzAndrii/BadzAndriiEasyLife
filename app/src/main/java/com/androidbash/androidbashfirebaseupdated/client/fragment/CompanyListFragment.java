package com.androidbash.androidbashfirebaseupdated.client.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.client.activity.ClientActivity;

public class CompanyListFragment extends Fragment {
    private View root;
    private ListView listViewCompany;
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start CompanyListFragment");
        root = inflater.inflate(R.layout.activity_list_company_for_users, container, false);

        initView();
        initListeners();

        return root;
    }

    private void initView() {
        listViewCompany = (ListView) root.findViewById(R.id.listViewCompany);
    }

    private void initListeners() {
        //attaching listener to listview
        listViewCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ((ClientActivity) getActivity()).nextListServiceForUser(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((ClientActivity) getActivity()).listCompany();

    }

}
