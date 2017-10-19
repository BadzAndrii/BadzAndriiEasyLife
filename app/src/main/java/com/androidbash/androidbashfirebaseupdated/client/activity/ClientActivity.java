package com.androidbash.androidbashfirebaseupdated.client.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.BoxAdapter;
import com.androidbash.androidbashfirebaseupdated.ClientOrder;
import com.androidbash.androidbashfirebaseupdated.Company;
import com.androidbash.androidbashfirebaseupdated.CompanyList;
import com.androidbash.androidbashfirebaseupdated.ListCompanyForUsers;
import com.androidbash.androidbashfirebaseupdated.ListServiceForUsers;
import com.androidbash.androidbashfirebaseupdated.Order;
import com.androidbash.androidbashfirebaseupdated.Product;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.Service;
import com.androidbash.androidbashfirebaseupdated.ServiceList;
import com.androidbash.androidbashfirebaseupdated.admin.fragment.ServiceTableFragment;
import com.androidbash.androidbashfirebaseupdated.client.fragment.CompanyListFragment;
import com.androidbash.androidbashfirebaseupdated.client.fragment.OrdersListFragment;
import com.androidbash.androidbashfirebaseupdated.client.fragment.RegisterOrderFragment;
import com.androidbash.androidbashfirebaseupdated.client.fragment.ServiceListFragment;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ClientActivity extends AppCompatActivity {
    String fileName;
    String serviceIdFromFragment;

    String companyNameForOrder;
    String serviceNameForOrder;
    //    for order
    DatabaseReference databaseCompanyOrder;
    SharedPreferences sPref;
   BoxAdapter boxAdapter;
    ArrayList<Product> products = new ArrayList<Product>();
//    end

//    for service

    public static final String SERVICE_ID = "serviceId";
//    public static final String TRACK_ID = "companyId";

    ListView listViewServices;
    //    ListView ListViewServices2;
    DatabaseReference databaseService;

    private ArrayList<Service> services = new ArrayList<>();
    //  для БД, щоб витягнути список сервісів для компанії
    private Firebase mRef;
    public ArrayList<String> mServices = new ArrayList<>();

//    end for service

    public static final String TRACK_ID = "companyId";

    DatabaseReference databaseCompany;
    List<Company> companies = new ArrayList<>();

    private ListView listViewCompany;
    private ListView ListViewServices2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("Activity:", "Start CLIENT ACTTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcompanyactivity);
        databaseCompany = FirebaseDatabase.getInstance().getReference("company");
        databaseService = FirebaseDatabase.getInstance().getReference("service");

//        for order
        sPref = getSharedPreferences("user_id", MODE_PRIVATE);
        databaseCompanyOrder = FirebaseDatabase.getInstance().getReference("orders");


//        end

//        knopka BACK
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void showCompanyList() {
        replaceFragment(new CompanyListFragment());
    }

    public void showServiceList() {
        replaceFragment(new ServiceListFragment());
    }

    public void showRegisterOrderFragment() {
        replaceFragment(new RegisterOrderFragment());
    }
    @Override
    protected void onStart() {
        super.onStart();
        showCompanyList();
    }

    public void showListServicesForUsersFromActivity() {
//        for services start
        // витягуємо із БД сервіси КОМПАНІЇ
//        Intent intent = getIntent();
        mRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/company/");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mServices);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        ListViewServices2 = (ListView) findViewById(R.id.listViewServices2);
        ListViewServices2.setAdapter(arrayAdapter);
//    Замість ID:-Ks.... треба передавати поточне ІД компанії
        final String compId = getFileName();
        Log.v("E_VALUE22", "compId:"+ compId);

        mRef.child(compId).child("servicesId").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
//                mServices.add(value);

                arrayAdapter.notifyDataSetChanged();


//  витягуємо із БД усі сервіси
                databaseService.orderByChild("serviceId").equalTo(value).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                            services.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Log.v("E_VALUE1","dataSnapshot:"+dataSnapshot);
                            Service service = postSnapshot.getValue(Service.class);
                            services.add(service);
                        }
                        ServiceList serviceListAdapter = new ServiceList(ClientActivity.this, services);
                        listViewServices.setAdapter(serviceListAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//

            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
//
//        end
    }

    public void nextListServiceForUser(int i) {

        Company company = companies.get(i);
//        Intent intent = new Intent(getApplicationContext(), ListServiceForUsers.class);
//        intent.putExtra(TRACK_ID, company.getCompanyId());

        Log.v("E_VALUE1", "CompanyId:" + company.getCompanyId());
//        startActivity(intent);
        //PACK DATA IN A BUNDLE
        Bundle bundle = new Bundle();
        bundle.putString("companyId", company.getCompanyId());

        //    TEST for ORDERS
        bundle.putString("companyName", company.getCompanyName());
//END TEST


        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        ServiceListFragment myFragment = new ServiceListFragment();
        myFragment.setArguments(bundle);
        //THEN NOW SHOW OUR FRAGMENT
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,myFragment).commit();


//        showServiceList();
    }

    public void listCompany() {
        databaseCompany.addValueEventListener(valueEventListener);
        listViewCompany = (ListView) findViewById(R.id.listViewCompany);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            companies.clear();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                Company company = postSnapshot.getValue(Company.class);
                companies.add(company);
            }
            CompanyList companyListAdapter = new CompanyList(ClientActivity.this, companies);
            listViewCompany.setAdapter(companyListAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


//    for order start
public void showOrderList(int i) {

    Service service = services.get(i);
//        Intent intent = new Intent(getApplicationContext(), ListServiceForUsers.class);
//        intent.putExtra(TRACK_ID, company.getCompanyId());

    Log.v("E_VALUE1", "ServiceId:" + service.getServiceId());
//        startActivity(intent);

    //PACK DATA IN A BUNDLE
    Bundle bundle = new Bundle();
    bundle.putString("serviceId", service.getServiceId());
//    TEST for ORDERS
    bundle.putString("serviceName", service.getServiceName());
//END TEST


    //PASS OVER THE BUNDLE TO OUR FRAGMENT
    RegisterOrderFragment myFragmentService = new RegisterOrderFragment();
    myFragmentService.setArguments(bundle);
    //THEN NOW SHOW OUR FRAGMENT
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,myFragmentService).commit();

//    showRegisterOrderFragment();
}
public void fillData() {


    double startTime = 9.00;
    double endTime = 17.599999999999998;
    double timeForOneClient = 0.15;
    double flag;
    int count=0;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    for (double hourInDay = startTime; hourInDay < endTime; ) {

        if(count ==4){
            hourInDay+=0.40;
            count=0;
        }
        else
        {
            flag = hourInDay + timeForOneClient;
            hourInDay = +flag;
            count++;
            if(count == 4 )
            {
                flag=+hourInDay+0.40;
            }
            products.add(new Product("Початок " + decimalFormat.format(hourInDay-0.15)," Кінець "+decimalFormat.format(flag), false));
//            System.out.println("hourInDay " + decimalFormat.format(hourInDay-0.15) + " flag " + decimalFormat.format(flag) + " count " + count );

        }


    }
    boxAdapter = new BoxAdapter(this, products);
    // настраиваем список
    ListView lvMain = (ListView) findViewById(R.id.lvMain);
    lvMain.setAdapter(boxAdapter);
}

    public void saveOrder() {


//        String orderTime = MyTextUtil.getText(editTextOrderTime);
        //test start
        String result = "Ви зареєструвались на:";
        String resultForDB = "";
        for (Product p : boxAdapter.getBox()) {
            if (p.box) {
                result += "\n" + p.name + " - " + p.price;
                resultForDB += p.name + " - " + p.price;
            }
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        String orderTime = resultForDB;
        //test end

//        String orderTime = editTextOrderTime.getText().toString().trim();
        Intent intent = getIntent();
        final String uid = sPref.getString("user_id", "");

//        String company_id = getIntent().getStringExtra(ListServiceForUsers.TRACK_ID);
        String company_id = getFileName();
        String service_id = getServiceId();

//        TEST FOR ORDER
        String company_name = getCompanyNameForOrder();
        String service_name = getServiceNameForOrder();

        Log.v("E_VALUE25", "company_name:" + company_name);
        Log.v("E_VALUE25", "service_name:" + service_name);
//        END

        Log.v("E_VALUE25", "company_id:" + company_id);
        Log.v("E_VALUE25", "service_id:" + service_id);

        if (!TextUtils.isEmpty(orderTime)) {
            String id = databaseCompanyOrder.push().getKey();

            Order order = new Order(id, company_id,company_name, service_id,service_name, orderTime, uid);

            databaseCompanyOrder.child(id).setValue(order);

            Toast.makeText(this, "Order saved", Toast.LENGTH_LONG).show();
//            editTextOrderTime.setText("");
        } else {
            Toast.makeText(this, "Please enter order start time", Toast.LENGTH_LONG).show();
        }

    }

//    end


//    for KNOPKA back in Action
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            finish();
        }
        return true;
    }

    //для передачі даних
    public String getFileName() {
        return fileName;
    }
    public String setFileName(String fileName) {
        this.fileName = fileName;
        return fileName;
    }

    public String getServiceId() {
        return serviceIdFromFragment;
    }
    public String setServiceId(String serviceIdFromFragment) {
        this.serviceIdFromFragment = serviceIdFromFragment;
        return serviceIdFromFragment;
    }


//    TEST FOR ORDERS
public String getCompanyNameForOrder() {
    return companyNameForOrder;
}
    public String setCompanyNameForOrder(String companyNameForOrder) {
        this.companyNameForOrder = companyNameForOrder;
        return companyNameForOrder;
    }

    public String getServiceNameForOrder() {
        return serviceNameForOrder;
    }
    public String setServiceNameForOrder(String serviceNameForOrder) {
        this.serviceNameForOrder = serviceNameForOrder;
        return serviceNameForOrder;
    }
//
}
