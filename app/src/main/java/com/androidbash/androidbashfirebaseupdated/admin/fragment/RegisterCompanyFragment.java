package com.androidbash.androidbashfirebaseupdated.admin.fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.Company;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.ServiceActivity;
import com.androidbash.androidbashfirebaseupdated.admin.activity.AdminActivity;
import com.androidbash.androidbashfirebaseupdated.utils.MyTextUtil;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RegisterCompanyFragment extends Fragment {
    public static final String TRACK_ID = "companyId";
    SharedPreferences sPref;
    private View root;
    DatabaseReference databaseCompany;
    Button buttonAddCompany;
    EditText editTextCompanyName;
    EditText editTextCompanyDescription;

    TextView textViewArtist;
    ListView listViewCompany;

    List<Company> companies = new ArrayList<>();

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        Log.v("Activity:", "Start REGISTERCOMPANYFRAGMENT");
        root = inflater.inflate(R.layout.activity_companies, container, false);

        initView();
        initListeners();



        return root;
    }

    private void initView() {
        buttonAddCompany = (Button) root.findViewById(R.id.buttonAddCompany);
        editTextCompanyName = (EditText) root.findViewById(R.id.editTextName);
        editTextCompanyDescription = (EditText) root.findViewById(R.id.editTextDescription);
        textViewArtist = (TextView) root.findViewById(R.id.textViewArtist);
        listViewCompany = (ListView) root.findViewById(R.id.listViewCompany);
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
                ((AdminActivity) getActivity()).nextService(i);
                //getting the selected artist
//                Company company = companies.get(i);

                //creating an intent
//                Intent intent = new Intent(getActivity().getApplicationContext(), ServiceActivity.class);
//                intent.putExtra(TRACK_ID, company.getCompanyId());

//                Log.v("E_VALUE1","Company_name:"+company.getCompanyName());
//                Log.v("E_VALUE1", "companyId:" + company.getCompanyId());
                //starting the activity with intent
//               startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {

        super.onStart();
        ((AdminActivity) getActivity()).listCompany();

    }


    private void saveCompany() {
        sPref = this.getActivity().getSharedPreferences("user_id", MODE_PRIVATE);
        //test
        String companyName = MyTextUtil.getText(editTextCompanyName);
//        String companyName = editTextCompanyName.getText().toString().trim();

        String companyDescription = MyTextUtil.getText(editTextCompanyDescription);
//        String companyDescription = editTextCompanyDescription.getText().toString().trim();
        final String uid = sPref.getString("user_id", "");

        if (!TextUtils.isEmpty(companyName)&&!TextUtils.isEmpty(companyDescription)) {
            ((AdminActivity) getActivity()).addCompanyBtn(companyName, companyDescription, uid);
            Toast.makeText(getActivity(), "Company saved", Toast.LENGTH_LONG).show();
            editTextCompanyName.setText("");
            editTextCompanyDescription.setText("");
        } else {
            Toast.makeText(getActivity(), "Please enter company name and description", Toast.LENGTH_LONG).show();
        }
    }

}
