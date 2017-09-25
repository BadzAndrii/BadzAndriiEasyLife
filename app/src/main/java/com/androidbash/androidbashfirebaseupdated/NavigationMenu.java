package com.androidbash.androidbashfirebaseupdated;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidbash.androidbashfirebaseupdated.admin.activity.AdminActivity;
import com.androidbash.androidbashfirebaseupdated.client.activity.ClientActivity;
import com.androidbash.androidbashfirebaseupdated.fragments.ContactUsFragment;
import com.androidbash.androidbashfirebaseupdated.fragments.FragmentIndex;
import com.androidbash.androidbashfirebaseupdated.fragments.ProfileFragment;
import com.androidbash.androidbashfirebaseupdated.fragments.SettingsFragment;
import com.androidbash.androidbashfirebaseupdated.tabs.dataFragment;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;

    SharedPreferences isUserAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity:","Start NavigationMenu");
        setContentView(R.layout.activity_navigation_menu);

//        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentIndex()).commit();


        initView();

        mAuth = FirebaseAuth.getInstance();

        isUserAdmin = getSharedPreferences("isAdmin", Context.MODE_PRIVATE);
        isUserAdmin.getBoolean("isAdmin", false);


    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();

//            mAuth.signOut();
            finish();
        }
        finishAffinity();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_camera) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentIndex()).commit();
        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new dataFragment()).commit();
        } else if (id == R.id.nav_slideshow) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ContactUsFragment()).commit();
        } else if (id == R.id.nav_manage) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
        }
//        else if (id == R.id.nav_share) {
//            Intent intent = new Intent(getApplicationContext(), com.androidbash.androidbashfirebaseupdated.profile.activity.ProfileActivity.class);
//            startActivity(intent);
//        }
//        else if (id == R.id.nav_send) {
////            Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
////            startActivity(intent2);
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
//        }
        else if (id == R.id.nav_send2) {
            Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent2);
        }
//        else if (id == R.id.nav_send3) {
//            Intent intent3 = new Intent(getApplicationContext(), CompanyActivity.class);
//            startActivity(intent3);
//        }
//        else if (id == R.id.nav_send4) {
//            Intent intent4 = new Intent(getApplicationContext(), ListCompanyForUsers.class);
//            startActivity(intent4);
//        }
        else if (id == R.id.nav_send5) {
            Intent intent5 = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(intent5);
        }
        else if (id == R.id.nav_send6) {
            Intent intent6 = new Intent(getApplicationContext(), ClientActivity.class);
            startActivity(intent6);
        }
        ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
