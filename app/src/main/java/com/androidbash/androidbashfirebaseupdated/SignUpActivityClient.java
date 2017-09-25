//package com.androidbash.androidbashfirebaseupdated;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.firebase.client.Firebase;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
////TODO: 01.09.17 видалити
//public class SignUpActivityClient extends AppCompatActivity {
//
//    private static final String TAG = "AndroidBash";
//    //Add YOUR Firebase Reference URL instead of the following URL
//    private Firebase mRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/");
//    private User_client userClient;
//    private EditText name;
//    private EditText phoneNumber;
//    private EditText email;
//    private EditText password;
//    private FirebaseAuth mAuth;
//    private ProgressDialog mProgressDialog;
//
//    SharedPreferences isUserAdmin;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.v("Activity:","Start SingUpActivityClient");
//        setContentView(R.layout.activity_sign_up_client);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        isUserAdmin = getSharedPreferences("isAdmin", MODE_PRIVATE);
//        SharedPreferences.Editor editor = isUserAdmin.edit();
//        editor.putBoolean("isAdmin", false);
////        editor.commit();
//        editor.apply();
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        initView();
//    }
//
//    private void initView() {
//        name = (EditText) findViewById(R.id.edit_text_username);
//        phoneNumber = (EditText) findViewById(R.id.edit_text_phone_number);
//        email = (EditText) findViewById(R.id.edit_text_new_email);
//        password = (EditText) findViewById(R.id.edit_text_new_password);
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    //This method sets up a new User_client by fetching the userAdmin entered details.
//    protected void setUpUser() {
//        userClient = new User_client();
//        userClient.setName(name.getText().toString());
//        userClient.setPhoneNumber(phoneNumber.getText().toString());
//        userClient.setEmail(email.getText().toString());
//        userClient.setPassword(password.getText().toString());
//        userClient.setIsAdmin(false);
//    }
//
//    public void onSignUpClicked(View view) {
//        createNewAccount(email.getText().toString(), password.getText().toString());
//        showProgressDialog();
//    }
//
//
//    private void createNewAccount(String email, String password) {
//        Log.d(TAG, "createNewAccount:" + email);
//        if (!validateForm()) {
//            return;
//        }
//        //This method sets up a new User_admin by fetching the userAdmin entered details.
//        setUpUser();
//        //This method  method  takes in an email address and password, validates them and then creates a new userAdmin
//        // with the createUserWithEmailAndPassword method.
//        // If the new account was created, the userAdmin is also signed in, and the AuthStateListener runs the onAuthStateChanged callback.
//        // In the callback, you can use the getCurrentUser method to get the userAdmin's account data.
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//                        hideProgressDialog();
//
//                        // If sign in fails, display a message to the userAdmin. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in userClient can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(SignUpActivityClient.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            onAuthenticationSucess(task.getResult().getUser());
//                        }
//
//
//                    }
//                });
//
//    }
//
//    private void onAuthenticationSucess(FirebaseUser mUser) {
//        // Write new userClient
//        saveNewUser(mUser.getUid(), userClient.getName(), userClient.getPhoneNumber(), userClient.getEmail(), userClient.getPassword(), userClient.getIsAdmin());
//        signOut();
//        // Go to LoginActivity
//        startActivity(new Intent(SignUpActivityClient.this, LoginActivity.class));
//        finish();
//    }
//
//    private void saveNewUser(String userId, String name, String phone, String email, String password, Boolean isAdmin) {
//        User_client userClient = new User_client(userId, name, phone, email, password, isAdmin);
//
//        mRef.child("users").child(userId).setValue(userClient);
//    }
//
//
//    private void signOut() {
//        mAuth.signOut();
//    }
//
//    //This method, validates email address and password
//    private boolean validateForm() {
//        boolean valid = true;
//
//        String userEmail = email.getText().toString();
//        if (TextUtils.isEmpty(userEmail)) {
//            email.setError("Required.");
//            valid = false;
//        } else {
//            email.setError(null);
//        }
//
//        String userName = name.getText().toString();
//        if (TextUtils.isEmpty(userName)) {
//            name.setError("Required.");
//            valid = false;
//        } else {
//            name.setError(null);
//        }
//
//        String userPassword = password.getText().toString();
//        if (TextUtils.isEmpty(userPassword)) {
//            password.setError("Required.");
//            valid = false;
//        } else {
//            password.setError(null);
//        }
//
//        return valid;
//    }
//
//
//    public void showProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(this);
//            mProgressDialog.setMessage(getString(R.string.loading));
//            mProgressDialog.setIndeterminate(true);
//        }
//
//        mProgressDialog.show();
//    }
//
//    public void hideProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
//    }
//
//}
