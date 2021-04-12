package com.duan1.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duan1.app.R;
import com.duan1.app.model.Chuong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class DocTruyenFragment extends Fragment {
    private TextView tvNoiDung, tvTenChuong;
    private FloatingActionButton fabNext;
    private List<Chuong> chuongList;
    private int pos;

    public DocTruyenFragment( List<Chuong> chuongList, int pos) {
        this.chuongList = chuongList;
        this.pos = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_truyen, container, false);
        tvNoiDung = (TextView) view.findViewById(R.id.tvNoidung);
        tvTenChuong = (TextView) view.findViewById(R.id.tvTenChuong);
        fabNext =  view.findViewById(R.id.fabNext);
        tvNoiDung.setText(chuongList.get(pos).getNoiDung());
        tvTenChuong.setText(chuongList.get(pos).getTenChuong());
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.right_to_left_enter,R.anim.right_to_left_exit,R.anim.left_to_right_enter,R.anim.left_to_right_exit).replace(R.id.fragment_container, new DocTruyenFragment(chuongList,pos+1)).addToBackStack(null).commit();

            }
        });
        return view;
    }
}