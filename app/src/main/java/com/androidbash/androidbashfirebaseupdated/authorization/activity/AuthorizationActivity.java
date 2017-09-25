package com.androidbash.androidbashfirebaseupdated.authorization.activity;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.authorization.dialog.Dialog1;
import com.androidbash.androidbashfirebaseupdated.NavigationMenu;
import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.User_client;
import com.androidbash.androidbashfirebaseupdated.authorization.fragment.SignInFragment;
import com.androidbash.androidbashfirebaseupdated.authorization.fragment.SignUpAdminFragment;
import com.androidbash.androidbashfirebaseupdated.authorization.fragment.SignUpClientFragment;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthorizationActivity extends AppCompatActivity {

  private static final String TAG = "AndroidBash";

  SharedPreferences sPref;

  DialogFragment dlg1;//todo назва!!!

  public User_client userAdmin;

  private User_client userClient;
  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;
  private ProgressDialog mProgressDialog;
  //Add YOUR Firebase Reference URL instead of the following URL
  Firebase mRef = new Firebase("https://androidbashfirebaseupdat-b96a7.firebaseio.com/");

  @Override protected void onCreate(Bundle savedInstanceState) {
    Log.v("Activity:", "Start AuthorizationActivity");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    initData();

    initView();
    //        initFirebase();
    //якщо користувач вже раніше залогігений відкрити наступне вікно
    initListener();
    dlg1 = new Dialog1();
  }
  private void initData() {
    mAuth = FirebaseAuth.getInstance();
  }

  private void initView() {
    //        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //        setSupportActionBar(toolbar);
  }

  private void initListener() {
    mAuthListener = new FirebaseAuth.AuthStateListener() {
      @Override public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser mUser = firebaseAuth.getCurrentUser();
        if (mUser != null) {
          // User_admin is signed in.
          Intent intent = new Intent(getApplicationContext(), NavigationMenu.class);

          //                    TEST
          String uid = mAuth.getCurrentUser().getUid();
          intent.putExtra("user_id", uid);
          sPref = getSharedPreferences("user_id", MODE_PRIVATE);
          SharedPreferences.Editor ed = sPref.edit();
          ed.putString("user_id", uid);
          ed.commit();
          //                    END TEST

          startActivity(intent);
          finish();
          Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
        } else {
          // User_admin is signed out
          Log.d(TAG, "onAuthStateChanged:signed_out");
        }
      }
    };
  }

  public void replaceFragment(Fragment fragment) {
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .addToBackStack(null)
        .commit();
  }

  public void showSignIn() {
    replaceFragment(new SignInFragment());
  }

  public void showSignUpClient() {
    replaceFragment(new SignUpClientFragment());
  }

  public void showSignUpAdmin() {
    replaceFragment(new SignUpAdminFragment());
  }

  //
  private void initFirebase() {
    //        mAuth = FirebaseAuth.getInstance();

    FirebaseUser mUser = mAuth.getCurrentUser();

    if (mUser != null) {
      // User_admin is signed in
      //            Intent intent_user_info = new Intent(getApplicationContext(), MainActivity.class);
      Intent intent = new Intent(getApplicationContext(), NavigationMenu.class);

      String uid = mAuth.getCurrentUser().getUid();
      intent.putExtra("user_id", uid);
      sPref = getSharedPreferences("user_id", MODE_PRIVATE);
      SharedPreferences.Editor ed = sPref.edit();
      ed.putString("user_id", uid);
      ed.commit();

      startActivity(intent);
      finish();

      Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
    }
  }

  @Override protected void onStart() {
    super.onStart();
    //        email = (EditText) findViewById(R.id.edit_text_email_id);
    //        password = (EditText) findViewById(R.id.edit_text_password);
    mAuth.addAuthStateListener(mAuthListener);

    showSignIn();
  }

  @Override public void onStop() {
    super.onStop();
    if (mAuthListener != null) {
      mAuth.removeAuthStateListener(mAuthListener);
    }
  }

  //REGISTRATION BUTTOM CLICK!!!
  public void onShowRegistrationDialog() {
    //        Intent intent = new Intent(this, SignUpActivityAdmin.class);
    //        startActivity(intent);
    dlg1.show(getFragmentManager(), "dlg1");
  }

  public void signIn(String email, String password) {
    userAdmin = new User_client();
    userAdmin.setEmail(email);
    userAdmin.setPassword(password);
    Log.d(TAG, "signIn:" + email);
    //        if (!validateForm()) {
    //            return;
    //        }

    showProgressDialog();

    mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the userAdmin. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in userAdmin can be handled in the listener.
            if (!task.isSuccessful()) {
              Log.w(TAG, "signInWithEmail", task.getException());
              Toast.makeText(AuthorizationActivity.this, "Authentication failed.",
                  Toast.LENGTH_SHORT).show();
            } else {
              //                            Intent intent_user_info = new Intent(getApplicationContext(), MainActivity.class);
              Intent intent = new Intent(AuthorizationActivity.this, NavigationMenu.class);
              String uid = mAuth.getCurrentUser().getUid();
              intent.putExtra("user_id", uid);
              //                            intent_user_info.putExtra("user_id", uid);

              sPref = getSharedPreferences("user_id", MODE_PRIVATE);
              SharedPreferences.Editor ed = sPref.edit();
              ed.putString("user_id", uid);
              ed.commit();

              startActivity(intent);
              finish();
            }

            hideProgressDialog();
          }
        });
    //
  }

  public void createNewAccount(String email, String password, String name, String phoneNumber,
      boolean isAdmin) {
    Log.d(TAG, "createNewAccount:" + email);

    userClient = new User_client();
    userClient.setName(name);
    userClient.setPhoneNumber(phoneNumber);
    userClient.setEmail(email);
    userClient.setPassword(password);
    userClient.setIsAdmin(isAdmin);

    mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {

            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
            hideProgressDialog();

            if (!task.isSuccessful()) {
              Toast.makeText(AuthorizationActivity.this, "Authentication failed.",
                  Toast.LENGTH_SHORT).show();
            } else {
              Toast.makeText(AuthorizationActivity.this, "Authentication SUCCESS.",
                  Toast.LENGTH_SHORT).show();
              onAuthenticationSuccess(task.getResult().getUser());
            }
          }
        });
    showProgressDialog();
  }

  public void onAuthenticationSuccess(FirebaseUser mUser) {
    // Write new userClient
    saveNewUser(mUser.getUid(), userClient.getName(), userClient.getPhoneNumber(),
        userClient.getEmail(), userClient.getPassword(), userClient.getIsAdmin());
    //        mAuth.signOut();
    FirebaseAuth.getInstance().signOut();
    // Go to LoginActivity
    //        FragmentManager fm = AuthorizationActivity.this.getSupportFragmentManager();
    //        fm.popBackStack();

    //ТУТ МАЄ БУТИ ПОВЕРНЕННЯ НА ЕКРАН ПОЕРЕДНІЙ ( ГОЛОВНИЙ) ПІСЛЯ НАТИСКАННЯ НА КНОПКУ РЕЄСТРАЦІЯ
    showSignIn();
    //        startActivity(new Intent(AuthorizationActivity.this, AuthorizationActivity.class));
    //        finish();
  }

  //// TODO: 01.09.17 використати загальний клас модель
  private void saveNewUser(String userId, String name, String phone, String email, String password,
      Boolean isAdmin) {
    User_client userClient = new User_client(userId, name, phone, email, password, isAdmin);

    mRef.child("users").child(userId).setValue(userClient);
  }

  public void showProgressDialog() {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(this);
      mProgressDialog.setMessage(getString(R.string.loading));
      mProgressDialog.setIndeterminate(true);
    }

    mProgressDialog.show();
  }

  public void hideProgressDialog() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
    }
  }
}
