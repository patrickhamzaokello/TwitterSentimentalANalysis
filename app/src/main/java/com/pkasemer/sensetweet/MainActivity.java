package com.pkasemer.sensetweet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.e("testing", "Permission is granted");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                Log.e("testing", "Permission is revoked");
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e("testing", "Permission is already granted");
        }


        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        Typeface typeface = getResources().getFont(R.font.myfont);
//        textView.setTypeface(typeface);
        new MyBroadcastReceiver();
        loadFragment(new HomeFragment());
        toolbar.setTitle("Tweet Sense");

    }


    @Override
    protected void onResume() {
        super.onResume();
        new MyBroadcastReceiver();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Tweet Sense");
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_favourite:
                    toolbar.setTitle("Favourites");
                    loadFragment(new FavouriteFragment());
                    return true;
                case R.id.navigation_search:
                    toolbar.setTitle("Guide");
                    loadFragment(new UserMapFragment());
                    return true;
                case R.id.navigation_filter:
                    toolbar.setTitle("Filter");
                    loadFragment(new FilterFragment());
                    return true;
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        new MyBroadcastReceiver();

    }


}