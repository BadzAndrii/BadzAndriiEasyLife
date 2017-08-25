package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Map;

public class ClientOrder extends AppCompatActivity {


    public static final String TRACK_ID = "companyId";

    Button buttonAddOrder;
    EditText editTextOrderTime;

    DatabaseReference databaseCompany;

    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_order);

        Intent intent = getIntent();

        sPref = getSharedPreferences("user_id",MODE_PRIVATE);
//        final String uid = sPref.getString("user_id","");
        databaseCompany = FirebaseDatabase.getInstance().getReference("orders");
//        databaseCompany = FirebaseDatabase.getInstance().getReference("company").child(uid);

        buttonAddOrder = (Button) findViewById(R.id.buttonAddOrder);
        editTextOrderTime = (EditText) findViewById(R.id.editTextTime);

        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrder();
            }
        });


    }

    private void saveOrder() {
        String orderTime = editTextOrderTime.getText().toString().trim();
        Intent intent = getIntent();
        final String uid = sPref.getString("user_id","");

        if (!TextUtils.isEmpty(orderTime)) {
            String id  = databaseCompany.push().getKey();

            Order order = new Order(id, intent.getStringExtra(ListServiceForUsers.SERVICE_ID), intent.getStringExtra(ListServiceForUsers.SERVICE_ID), orderTime, uid);

            databaseCompany.child(id).setValue(order);

            Toast.makeText(this, "Order saved", Toast.LENGTH_LONG).show();
            editTextOrderTime.setText("");
        }
        else {
            Toast.makeText(this, "Please enter order start time", Toast.LENGTH_LONG).show();
        }
    }
}
