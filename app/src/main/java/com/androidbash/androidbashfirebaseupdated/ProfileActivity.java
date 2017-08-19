package com.androidbash.androidbashfirebaseupdated;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
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

    private TextView ProfileCompany_name;

    private TextView ProfilePhoneNumber;

    private TextView ProfileCompany_description;

    private TextView ProfileEmail;

    private TextView text_view_company_name;

    private TextView text_view_company_description;


    SharedPreferences sPref;
    SharedPreferences User_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile Information");

        ProfileCompany_description = (TextView) findViewById(R.id.profile_company_description);
        ProfileCompany_name = (TextView) findViewById(R.id.profile_company_name);
        ProfileName = (TextView)findViewById(R.id.profile_name);
        ProfileEmail = (TextView)findViewById(R.id.profile_email);
        ProfilePhoneNumber = (TextView)findViewById(R.id.profile_phone);

        text_view_company_name = (TextView)findViewById(R.id.text_view_company_name);
        text_view_company_description = (TextView)findViewById(R.id.text_view_company_description);

        //Creates a reference for  your Firebase database
        //Add YOUR Firebase Reference URL instead of the following URL
        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/users/");
        mAuth = FirebaseAuth.getInstance();

//        Badz codding
        sPref = getSharedPreferences("user_id",MODE_PRIVATE);
        final String uid = sPref.getString("user_id","");
        myFirebaseRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> map = dataSnapshot.getValue(Map.class);

                String company_description = map.get("company_description");
                String company_name = map.get("company_name");
                String email = map.get("email");
                String id = map.get("id");
                String name = map.get("name");
                String password = map.get("password");
                String phoneNumber = map.get("phoneNumber");

                ProfileCompany_description.setText(company_description);
                ProfileCompany_name.setText(company_name);
                ProfileName.setText(name);
                ProfileEmail.setText(email);
                ProfilePhoneNumber.setText(phoneNumber);


                if (ProfileCompany_description.getText().toString().equals(""))
                {
                    ProfileCompany_description.setVisibility(View.GONE);
                    text_view_company_name.setVisibility(View.GONE);
            }
                else
            {
                ProfileCompany_description.setVisibility(View.VISIBLE);
                text_view_company_name.setVisibility(View.VISIBLE);
            }

                if (ProfileCompany_name.getText().toString().equals(""))
                {
                    ProfileCompany_name.setVisibility(View.GONE);
                    text_view_company_description.setVisibility(View.GONE);
                }
                else
                {
                    ProfileCompany_name.setVisibility(View.VISIBLE);
                    text_view_company_description.setVisibility(View.VISIBLE);
                }
                Log.v("E_VALUE","company_description:"+company_description);
                Log.v("E_VALUE","company_name:"+company_name);
                Log.v("E_VALUE","email:"+email);
                Log.v("E_VALUE","id:"+id);
                Log.v("E_VALUE","name:"+name);
                Log.v("E_VALUE","password:"+password);
                Log.v("E_VALUE","phoneNumber:"+phoneNumber);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        name = (TextView) findViewById(R.id.text_view_name);
//        telefon = (TextView) findViewById(R.id.text_view_telefon);
//        welcomeText = (TextView) findViewById(R.id.text_view_welcome);
//        changeButton = (Button) findViewById(R.id.button_change);
//        revertButton = (Button) findViewById(R.id.button_revert);
//
//
//        sPref = getSharedPreferences("user_id",MODE_PRIVATE);
//        final String uid = sPref.getString("user_id","");
//
//
//        //Get the uid for the currently logged in User_admin from intent data passed to this activity
////        String uid = getIntent().getExtras().getString("user_id");
//        //Get the imageUrl  for the currently logged in User_admin from intent data passed to this activity
//
//
//        //Referring to the name of the User_admin who has logged in currently and adding a valueChangeListener
//        myFirebaseRef.child(uid).child("name").addValueEventListener(new ValueEventListener() {
//            //onDataChange is called every time the name of the User_admin changes in your Firebase Database
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Inside onDataChange we can get the data as an Object from the dataSnapshot
//                //getValue returns an Object. We can specify the type by passing the type expected as a parameter
//                String data = dataSnapshot.getValue(String.class);
//                name.setText("Привіт " + data + ", ");
//            }
//
//            //onCancelled is called in case of any error
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//        //Referring to the name of the User_admin who has logged in currently and adding a valueChangeListener
//        myFirebaseRef.child(uid).child("id").addValueEventListener(new ValueEventListener() {
//            //onDataChange is called every time the name of the User_admin changes in your Firebase Database
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Inside onDataChange we can get the data as an Object from the dataSnapshot
//                //getValue returns an Object. We can specify the type by passing the type expected as a parameter
//                String mobile_phone = dataSnapshot.getValue(String.class);
//                telefon.setText("id: " + mobile_phone + ", ");
//            }
//
//            //onCancelled is called in case of any error
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//        //A firebase reference to the welcomeText can be created in following ways :
//        // You can use this :
//        //Firebase myAnotherFirebaseRefForWelcomeText=new Firebase("https://androidbashfirebaseupdat-bd094.firebaseio.com/welcomeText");*/
//        //OR as shown below
//        myFirebaseRef.child("welcomeText").addValueEventListener(new ValueEventListener() {
//            //onDataChange is called every time the data changes in your Firebase Database
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Inside onDataChange we can get the data as an Object from the dataSnapshot
//                //getValue returns an Object. We can specify the type by passing the type expected as a parameter
//                String data = dataSnapshot.getValue(String.class);
//                welcomeText.setText(data);
//            }
//
//            //onCancelled is called in case of any error
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        //onClicking changeButton the value of the welcomeText in the Firebase database gets changed
//        changeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myFirebaseRef.child(uid).child("welcomeText3").setValue("Android App @ Badz3");
//            }
//        });
//
//        //onClicking revertButton the value of the welcomeText in the Firebase database gets changed
//        revertButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myFirebaseRef.child(uid).child("welcomeText").setValue("Ласкаво просимо до нашого додатку, ми створені щоб зробити твоє життя краще!");
//            }
//        });
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        if (id == R.id.action_logout) {
//            mAuth.signOut();
//            finish();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //REGISTRATION BUTTOM CLICK!!!
    public void onSignUpClicked2(View view) {

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
                            public void onClick(DialogInterface dialog,int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                ProfileName.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Відміна",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();

    }

}



