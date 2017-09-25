package com.androidbash.androidbashfirebaseupdated.admin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.CompanyActivity;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.Service;
import com.androidbash.androidbashfirebaseupdated.admin.activity.AdminActivity;
import com.androidbash.androidbashfirebaseupdated.authorization.activity.AuthorizationActivity;
import com.androidbash.androidbashfirebaseupdated.utils.MyTextUtil;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class ServiceTableFragment extends Fragment {
    private View root;
    private String fileName;

    Button buttonAddService;
    EditText editTextServiceName;
    EditText editTextServiceDescription;
    EditText editTextServiceTime;
    ListView listViewServices;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start ServiceTableFragment");
        root = inflater.inflate(R.layout.activity_service, container, false);

        initView();
        initListener();

        //UNPACK OUR DATA FROM OUR BUNDLE
        String companyIdFromAdminActivity = this.getArguments().getString("companyId").toString();

        fileName = ((AdminActivity) getActivity()).setFileName(companyIdFromAdminActivity);
        Log.v("Activity:", "CompanyId"+companyIdFromAdminActivity);

        return root;
    }

    private void initView() {
        buttonAddService = (Button) root.findViewById(R.id.buttonAddService);
        editTextServiceName = (EditText) root.findViewById(R.id.editTextServiceName);
        editTextServiceDescription = (EditText) root.findViewById(R.id.editTextServiceDescription);
        editTextServiceTime = (EditText) root.findViewById(R.id.editTextServiceTime);
        listViewServices = (ListView) root.findViewById(R.id.listViewServices);
    }

    @Override
    public void onStart() {

        super.onStart();
        ((AdminActivity) getActivity()).listServices();

    }

    private void initListener() {
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveService();
            }
        });
    }
    public void saveService() {

        String serviceName; String serviceDescription; String serviceTime;

         serviceName = MyTextUtil.getText(editTextServiceName);
         serviceDescription = MyTextUtil.getText(editTextServiceDescription);
         serviceTime = MyTextUtil.getText(editTextServiceTime);

        ((AdminActivity) getActivity()).createNewService(serviceName, serviceDescription, serviceTime);

        editTextServiceName.setText("");
        editTextServiceDescription.setText("");
        editTextServiceTime.setText("");
    }

}
