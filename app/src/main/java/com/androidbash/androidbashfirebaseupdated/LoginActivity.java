//package com.androidbash.androidbashfirebaseupdated;
//
//import android.app.DialogFragment;
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
//public class LoginActivity extends AppCompatActivity {
//
//    SharedPreferences sPref;
//
//    DialogFragment dlg1;
//
//    private static final String TAG = "AndroidBash";
//    public User_client userAdmin;
//    private EditText email;
//    private EditText password;
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//    private ProgressDialog mProgressDialog;
//    //Add YOUR Firebase Reference URL instead of the following URL
//    Firebase mRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/users/");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        initView();
//        initFirebase();
//        initListener();
//
//        dlg1 = new Dialog1();
//    }
//
//    private void initView()
//    {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//    }
//    private void initFirebase() {
//        mAuth = FirebaseAuth.getInstance();
//
//        FirebaseUser mUser = mAuth.getCurrentUser();
//        if (mUser != null) {
//            // User_admin is signed in
////            Intent intent_user_info = new Intent(getApplicationContext(), MainActivity.class);
//            Intent intent = new Intent(getApplicationContext(), NavigationMenu.class);
//
//            String uid = mAuth.getCurrentUser().getUid();
////            String image=mAuth.getCurrentUser().getPhotoUrl().toString();
//            intent.putExtra("user_id", uid);
////            intent_user_info.putExtra("user_id", uid);
//
//            sPref = getSharedPreferences("user_id", MODE_PRIVATE);
//            SharedPreferences.Editor ed = sPref.edit();
//            ed.putString("user_id", uid);
//            ed.commit();
//
//            startActivity(intent);
//            finish();
//            Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
//        }
//    }
//
//    private void initListener() {
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser mUser = firebaseAuth.getCurrentUser();
//                if (mUser != null) {
//                    // User_admin is signed in.
//                    Intent intent = new Intent(getApplicationContext(), NavigationMenu.class);
//                    startActivity(intent);
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
//                } else {
//                    // User_admin is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//
//            }
//        };
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        email = (EditText) findViewById(R.id.edit_text_email_id);
//        password = (EditText) findViewById(R.id.edit_text_password);
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
//
//
//    protected void setUpUser() {
//        userAdmin = new User_client();
//        userAdmin.setEmail(email.getText().toString());
//        userAdmin.setPassword(password.getText().toString());
//    }
//
//    //REGISTRATION BUTTOM CLICK!!!
//    public void onSignUpClicked(View view) {
////        Intent intent = new Intent(this, SignUpActivityAdmin.class);
////        startActivity(intent);
//        dlg1.show(getFragmentManager(), "dlg1");
//    }
//
//    public void onLoginClicked(View view) {
//        setUpUser();
//        signIn(email.getText().toString(), password.getText().toString());
//    }
//
//    private void signIn(String email, String password) {
//        Log.d(TAG, "signIn:" + email);
//        if (!validateForm()) {
//            return;
//        }
//
//        showProgressDialog();
//
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the userAdmin. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in userAdmin can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "signInWithEmail", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
////                            Intent intent_user_info = new Intent(getApplicationContext(), MainActivity.class);
//                            Intent intent = new Intent(getApplicationContext(), NavigationMenu.class);
//                            String uid = mAuth.getCurrentUser().getUid();
//                            intent.putExtra("user_id", uid);
////                            intent_user_info.putExtra("user_id", uid);
//
//
//                            sPref = getSharedPreferences("user_id", MODE_PRIVATE);
//                            SharedPreferences.Editor ed = sPref.edit();
//                            ed.putString("user_id", uid);
//                            ed.commit();
//
//
//                            startActivity(intent);
//                            finish();
//                        }
//
//                        hideProgressDialog();
//                    }
//                });
//        //
//    }
//
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
//
//}
