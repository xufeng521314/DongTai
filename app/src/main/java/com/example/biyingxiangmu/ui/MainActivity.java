package com.example.biyingxiangmu.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biyingxiangmu.R;
import com.example.biyingxiangmu.fragments.OwnFragment;
import com.example.biyingxiangmu.fragments.TrendsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mFrames;

    private FragmentManager supportFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


//            fragmentTransaction.add(R.id.frames,trendsFragment).add(R.id.frames,ownFragment).show(trendsFragment).hide(ownFragment).commit();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //FragmentTransaction fragmentTransaction1 = supportFragmentManager.beginTransaction();
                    //fragmentTransaction1.show(trendsFragment).hide(ownFragment).commit();
                    fragmentTransaction.replace(R.id.frames,trendsFragment).commit();
                    break;
                case R.id.navigation_dashboard:
                    fragmentTransaction.replace(R.id.frames,trendsFragment).commit();
                    break;
                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.frames,trendsFragment).commit();
                    break;
                case R.id.navigation_email:
                    fragmentTransaction.replace(R.id.frames,trendsFragment).commit();

                    break;
                case R.id.navigation_login:
                    //FragmentTransaction fragmentTransaction2 = supportFragmentManager.beginTransaction();
                    //fragmentTransaction2.show(ownFragment).hide(trendsFragment).commit();
                    fragmentTransaction.replace(R.id.frames,ownFragment).commit();
                    break;
            }
            return false;
        }
    };
    private TrendsFragment trendsFragment;
    private OwnFragment ownFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trendsFragment = new TrendsFragment();
        ownFragment = new OwnFragment();
        supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mFrames = findViewById(R.id.frames);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
