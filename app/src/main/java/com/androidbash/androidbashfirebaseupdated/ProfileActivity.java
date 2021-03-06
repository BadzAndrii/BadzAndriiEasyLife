package com.androidbash.androidbashfirebaseupdated;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    final Context context = this;

    private Firebase myFirebaseRef;
    private FirebaseAuth mAuth;

    private TextView ProfileName;
    private TextView ProfilePhoneNumber;
    private TextView ProfileEmail;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity:","Start ProfileActivity");
        setContentView(R.layout.activity_profile_bar);

        initView();

        //Creates a reference for  your Firebase database
        //Add YOUR Firebase Reference URL instead of the following URL
        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/users/");
        mAuth = FirebaseAuth.getInstance();

        initFirebase();
        //        knopka BACK
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    private void initFirebase() {
        sPref = getSharedPreferences("user_id", MODE_PRIVATE);
        final String uid = sPref.getString("user_id", "");
        myFirebaseRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);

                String email = map.get("email");
                String id = map.get("id");
                String name = map.get("name");
                String password = map.get("password");
                String phoneNumber = map.get("phoneNumber");


                ProfileName.setText(name);
                ProfileEmail.setText(email);
                ProfilePhoneNumber.setText(phoneNumber);

                Log.v("E_VALUE", "email:" + email);
                Log.v("E_VALUE", "id:" + id);
                Log.v("E_VALUE", "name:" + name);
                Log.v("E_VALUE", "password:" + password);
                Log.v("E_VALUE", "phoneNumber:" + phoneNumber);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void initView() {
        ProfileName = (TextView) findViewById(R.id.profile_name);
        ProfileEmail = (TextView) findViewById(R.id.profile_email);
        ProfilePhoneNumber = (TextView) findViewById(R.id.profile_phone);

    }

    public void onChangeBtnName(View view) {

        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.change_name_alert_dlgs, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                              ProfileName.setText(userInput.getText());

                                sPref = getSharedPreferences("user_id", MODE_PRIVATE);
                                final String uid = sPref.getString("user_id", "");

                                myFirebaseRef.child(uid).child("name").setValue(ProfileName.getText().toString());
                            }
                        })
                .setNegativeButton("Відміна",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();

    }
    public void onChangeBtnEmail(View view) {

        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.change_name_alert_dlgs, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        userInput.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:

                                ProfileEmail.setText(userInput.getText());

                                sPref = getSharedPreferences("user_id", MODE_PRIVATE);
                                final String uid = sPref.getString("user_id", "");

                                myFirebaseRef.child(uid).child("email").setValue(ProfileEmail.getText().toString());
                            }
                        })
                .setNegativeButton("Відміна",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();

    }

    public void onChangeBtnNubmer(View view) {

        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.change_name_alert_dlgs, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        userInput.setInputType(InputType.TYPE_CLASS_PHONE);
        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:

                                ProfilePhoneNumber.setText(userInput.getText());

                                sPref = getSharedPreferences("user_id", MODE_PRIVATE);
                                final String uid = sPref.getString("user_id", "");

                                myFirebaseRef.child(uid).child("phoneNumber").setValue(ProfilePhoneNumber.getText().toString());
                            }
                        })
                .setNegativeButton("Відміна",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();

    }

    //    for KNOPKA back in Action
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            finish();
        }
        return true;
    }
}



