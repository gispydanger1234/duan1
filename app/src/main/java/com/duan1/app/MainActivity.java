package com.duan1.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import database.DBHelper;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper=new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dbHelper.addDB();
        BottomNavigationView navigationView=findViewById(R.id.bottomNavi);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Doc_Truyen_Fragment()).commit();
        navigationView.setOnNavigationItemSelectedListener(navListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;
            switch (item.getItemId()){
                case R.id.itemDocTruyen:
                    selectedFragment= new Doc_Truyen_Fragment();
                    break;
                case R.id.itemLichSu:
                    selectedFragment= new Lich_Su_Fragment();
                    break;
                case R.id.itemYeuThich:
                    selectedFragment= new YeuThichFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;

        }
    };
}