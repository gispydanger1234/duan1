package com.duan1.app.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.app.adapter.TruyenAdapter;
import com.duan1.app.model.Truyen;
import com.duan1.app.R;

import java.util.ArrayList;
import java.util.List;

import com.duan1.app.database.DBHelper;


public class DanhSachTruyenFragment extends Fragment {
    private EditText editTextTextPersonName2;
    private EditText editTextTextPersonName3;
    private EditText editTextTextPersonName4;
    private ImageView imageView3;
    private TextView textView;
    private TextView textView2;
    private RecyclerView rvDoctruyen;
    private DBHelper dbHelper = new DBHelper(getActivity());
    private List<Truyen> objTruyens;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach_truyen, container, false);
        initView(view);
        objTruyens = new ArrayList<>();
        objTruyens = dbHelper.showAllTruyen(getActivity());
        System.out.println(objTruyens.size());
        TruyenAdapter adapter = new TruyenAdapter(getActivity(), objTruyens, new TruyenAdapter.OnClick() {
            @Override
            public void onclick(int pos) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoTaTruyenFragment(objTruyens.get(pos))).addToBackStack(null).commit();
            }
        });
        rvDoctruyen.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvDoctruyen.setHasFixedSize(true);
        rvDoctruyen.setAdapter(adapter);

        return view;
    }

    private void initView(View view) {
        editTextTextPersonName2 = (EditText) view.findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = (EditText) view.findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = (EditText) view.findViewById(R.id.editTextTextPersonName4);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        textView = (TextView) view.findViewById(R.id.textView);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        rvDoctruyen = (RecyclerView) view.findViewById(R.id.rvDoctruyen);
    }
}