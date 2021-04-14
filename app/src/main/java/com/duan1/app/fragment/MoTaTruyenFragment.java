package com.duan1.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.duan1.app.adapter.ChuongAdapter;
import com.duan1.app.model.Chuong;
import com.duan1.app.model.Truyen;
import com.duan1.app.R;

import java.util.ArrayList;
import java.util.List;

import com.duan1.app.database.DBHelper;

public class MoTaTruyenFragment extends Fragment {

    private ImageView imgAnh;
    private TextView tvTacgia;
    private TextView tvTheloai;
    private TextView tvTentruyen;
    private ImageView imgYeuthich;
    private TextView tvTomtat;
    private ListView lvChuong;
    private DBHelper dbHelper = new DBHelper(getActivity());
    private List<Chuong> obj_chuongs;
    public Truyen obj_truyen;
    public MoTaTruyenFragment(Truyen obj_truyen) {
        this.obj_truyen = obj_truyen;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mo_ta_truyen, container, false);
        initView(view);
        obj_chuongs = new ArrayList<>();
        obj_chuongs = dbHelper.showAllChuongList(obj_truyen.getTen(), getActivity());
        ChuongAdapter chuongAdapter = new ChuongAdapter(getActivity(), obj_chuongs);
        lvChuong.setAdapter(chuongAdapter);
        lvChuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DocTruyenFragment( obj_chuongs, position)).addToBackStack(null).commit();
            }
        });
        imgAnh.setImageBitmap(obj_truyen.getBitmap());
        tvTentruyen.setText(obj_truyen.getTen());
        tvTacgia.setText(obj_truyen.getTacGia());
        tvTheloai.setText(obj_truyen.getTheLoai());
        tvTomtat.setText(obj_truyen.getMoTa());
        imgYeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgYeuthich.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
            }
        });
        return view;
    }

    private void initView(View view) {
        imgAnh = (ImageView) view.findViewById(R.id.imgAnh);
        tvTentruyen = (TextView) view.findViewById(R.id.tvTentruyen);
        tvTacgia = (TextView) view.findViewById(R.id.tvTacgia);
        tvTheloai = (TextView) view.findViewById(R.id.tvTheloai);
        imgYeuthich = (ImageView) view.findViewById(R.id.imgYeuthich);
        tvTomtat = (TextView) view.findViewById(R.id.tvTomtat);
        lvChuong = (ListView) view.findViewById(R.id.lvChuong);
    }

}