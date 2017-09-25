package com.androidbash.androidbashfirebaseupdated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.utils.MyTextUtil;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//Формування запису (створення запису на послугу в БД)

//МОЖНА ВИДАЛИТИ


public class ClientOrder extends AppCompatActivity {
//    tesst start
    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;
//    test end

    Button buttonAddOrder;
//    EditText editTextOrderTime;

    DatabaseReference databaseCompany;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity:","Start ClientOrder");
        setContentView(R.layout.activity_client_order);

        sPref = getSharedPreferences("user_id", MODE_PRIVATE);
        databaseCompany = FirebaseDatabase.getInstance().getReference("orders");

        initView();
        initListeners();

//        test start
        // создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, products);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
//        test end
    }
//    test start
// генерируем данные для адаптера
void fillData() {
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


}

    // выводим информацию о корзине
//    public void showResult(View v) {
//        String result = "Ви зареєструались на:";
//        for (Product p : boxAdapter.getBox()) {
//            if (p.box)
//                result += "\n" + p.name + " - " +p.price;
//        }
//        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//    }

//test end
    private void initView() {
        buttonAddOrder = (Button) findViewById(R.id.buttonAddOrder);
//        editTextOrderTime = (EditText) findViewById(R.id.editTextTime);
    }

    private void initListeners() {
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrder();
            }
        });
    }

    private void saveOrder() {

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

        String company_id = getIntent().getStringExtra(ListServiceForUsers.TRACK_ID);

        if (!TextUtils.isEmpty(orderTime)) {
            String id = databaseCompany.push().getKey();

            Order order = new Order(id, company_id, intent.getStringExtra(ListServiceForUsers.SERVICE_ID), orderTime, uid);

            databaseCompany.child(id).setValue(order);

            Toast.makeText(this, "Order saved", Toast.LENGTH_LONG).show();
//            editTextOrderTime.setText("");
        } else {
            Toast.makeText(this, "Please enter order start time", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "FINISHED", Toast.LENGTH_LONG).show();
        finish();
    }
}
