package com.androidbash.androidbashfirebaseupdated;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AndroidBash";
    private Firebase myFirebaseRef;
    private FirebaseAuth mAuth;
    private TextView name;
    private TextView telefon;
    private TextView welcomeText;
    private Button changeButton;
    private Button revertButton;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Profile Information");

        //Creates a reference for  your Firebase database
        //Add YOUR Firebase Reference URL instead of the following URL
        myFirebaseRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/users/");
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        name = (TextView) findViewById(R.id.text_view_name);
        telefon = (TextView) findViewById(R.id.text_view_telefon);
        welcomeText = (TextView) findViewById(R.id.text_view_welcome);
        changeButton = (Button) findViewById(R.id.button_change);
        revertButton = (Button) findViewById(R.id.button_revert);


        sPref = getSharedPreferences("user_id",MODE_PRIVATE);
        final String uid = sPref.getString("user_id","");


        //Get the uid for the currently logged in User_admin from intent data passed to this activity
//        String uid = getIntent().getExtras().getString("user_id");
        //Get the imageUrl  for the currently logged in User_admin from intent data passed to this activity


        //Referring to the name of the User_admin who has logged in currently and adding a valueChangeListener
        myFirebaseRef.child(uid).child("name").addValueEventListener(new ValueEventListener() {
            //onDataChange is called every time the name of the User_admin changes in your Firebase Database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Inside onDataChange we can get the data as an Object from the dataSnapshot
                //getValue returns an Object. We can specify the type by passing the type expected as a parameter
                String data = dataSnapshot.getValue(String.class);
                name.setText("Привіт " + data + ", ");
            }

            //onCancelled is called in case of any error
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //A firebase reference to the welcomeText can be created in following ways :
        // You can use this :
        //Firebase myAnotherFirebaseRefForWelcomeText=new Firebase("https://androidbashfirebaseupdat-bd094.firebaseio.com/welcomeText");*/
        //OR as shown below
        myFirebaseRef.child("welcomeText").addValueEventListener(new ValueEventListener() {
            //onDataChange is called every time the data changes in your Firebase Database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Inside onDataChange we can get the data as an Object from the dataSnapshot
                //getValue returns an Object. We can specify the type by passing the type expected as a parameter
                String data = dataSnapshot.getValue(String.class);
                welcomeText.setText(data);
            }

            //onCancelled is called in case of any error
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //onClicking changeButton the value of the welcomeText in the Firebase database gets changed
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFirebaseRef.child("welcomeText").setValue("Android App @ Badzzzzzzzzzzzzzzzzzz");
            }
        });

        //onClicking revertButton the value of the welcomeText in the Firebase database gets changed
        revertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFirebaseRef.child("welcomeText").setValue("zzzzzzzzzzЛаскаво просимо до нашого додатку, ми створені щоб зробити твоє життя краще!");
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            mAuth.signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}



