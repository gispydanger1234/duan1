package com.duan1.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.duan1.app.R;
import com.duan1.app.fragment.DanhSachTruyenFragment;
import com.duan1.app.fragment.LichSuFragment;
import com.duan1.app.fragment.YeuThichFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.duan1.app.database.DBHelper;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper=new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dbHelper.addDB();
        BottomNavigationView navigationView=findViewById(R.id.bottomNavi);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DanhSachTruyenFragment()).commit();
        navigationView.setOnNavigationItemSelectedListener(navListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;
            switch (item.getItemId()){
                case R.id.itemDocTruyen:
                    selectedFragment= new DanhSachTruyenFragment();
                    break;
                case R.id.itemLichSu:
                    selectedFragment= new LichSuFragment();
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