package com.duan1.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.duan1.app.R;
import com.duan1.app.adapter.LichSuAdapter;
import com.duan1.app.database.DBHelper;
import com.duan1.app.model.LichSu;


public class LichSuFragment extends Fragment {
    DBHelper dbHelper=new DBHelper(getContext());
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lich_su, container, false);
        initView(view);
        LichSuAdapter lichSuAdapter=new LichSuAdapter(getContext(), dbHelper.showLichSuList(getContext()), new LichSuAdapter.LichSuOnClick() {
            @Override
            public void onclick(int pos) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,
                                new DocTruyenFragment(
                                dbHelper.showAllChuongList(
                                        dbHelper.showLichSuList(getContext()).get(pos).getTen(),getContext()), dbHelper.showLichSuList(getContext()).get(pos).getChuong()-1))
                        .addToBackStack(null).commit();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(lichSuAdapter);
        System.out.println(getContext());
        return view;

    }
    private void initView(View view) {
        recyclerView=view.findViewById(R.id.rvList);
    }
}