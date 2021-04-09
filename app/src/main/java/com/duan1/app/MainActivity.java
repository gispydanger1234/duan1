package com.duan1.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import database.DBHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DBHelper dbHelper=new DBHelper(this);
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
                    selectedFragment= new Doc_Truyen_Fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}