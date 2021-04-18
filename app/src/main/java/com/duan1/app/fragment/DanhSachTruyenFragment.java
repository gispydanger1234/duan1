package com.duan1.app.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.app.adapter.TruyenAdapter;
import com.duan1.app.dialog.DialogSearchBy;
import com.duan1.app.dialog.DialogTheLoai;
import com.duan1.app.model.Truyen;
import com.duan1.app.R;

import java.util.ArrayList;
import java.util.List;

import com.duan1.app.database.DBHelper;


public class DanhSachTruyenFragment extends Fragment implements DialogSearchBy.SendSearchBy,DialogTheLoai.SendTheLoai {
    private EditText editSearch,edtBy,edtCategory;
    private ImageView imgSearch;
    private RecyclerView rvDoctruyen;
    private DBHelper dbHelper = new DBHelper(getActivity());
    private List<Truyen> objTruyens;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_danh_sach_truyen, container, false);
        initView(view);
        objTruyens = new ArrayList<>();
        objTruyens = dbHelper.showAllTruyen(getActivity());
        TruyenAdapter truyenAdapter=new TruyenAdapter(getContext(), objTruyens, new TruyenAdapter.TruyenOnClick() {
            @Override
            public void onclick(int pos) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoTaTruyenFragment(objTruyens.get(pos))).addToBackStack(null).commit();
            }
        });
        rvDoctruyen.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvDoctruyen.setHasFixedSize(true);
        rvDoctruyen.setAdapter(truyenAdapter);


        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objTruyens.clear();
            }
        });
        edtBy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DialogSearchBy dialogSearchBy=new DialogSearchBy(getActivity());
                dialogSearchBy.setTargetFragment(DanhSachTruyenFragment.this,1);
                dialogSearchBy.show(getFragmentManager(),"");
                return false;
            }
        });
        edtCategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DialogTheLoai dialogTheLoai=new DialogTheLoai(getActivity());
                dialogTheLoai.setTargetFragment(DanhSachTruyenFragment.this,1);
                dialogTheLoai.show(getFragmentManager(),"");
                return false;
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timkiem=editSearch.getText().toString();
                String theo=edtBy.getText().toString();
                String theloai=edtCategory.getText().toString();
                if (theo.equals("Tất Cả")){
                    theo="";
                }
                if (theloai.equals("Tất Cả")){
                    theloai="";
                }
                objTruyens.clear();
                objTruyens=dbHelper.searchBySearchBar(timkiem,theo,theloai,getContext());
                TruyenAdapter adapter = new TruyenAdapter(getActivity(), objTruyens, new TruyenAdapter.TruyenOnClick() {
                    @Override
                    public void onclick(int pos) {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoTaTruyenFragment(objTruyens.get(pos))).addToBackStack(null).commit();

                    }
                });
                rvDoctruyen.setLayoutManager(new GridLayoutManager(getActivity(),2));
                rvDoctruyen.setHasFixedSize(true);
                rvDoctruyen.setAdapter(adapter);
            }
        });
        return view;
    }

    private void initView(View view) {
        editSearch = (EditText) view.findViewById(R.id.edtSearch);
        edtBy = (EditText) view.findViewById(R.id.edtBy);
        edtCategory = (EditText) view.findViewById(R.id.edtCategory);
        rvDoctruyen = (RecyclerView) view.findViewById(R.id.rvDoctruyen);
        imgSearch=view.findViewById(R.id.imgSearch);
    }

    @Override
    public void sendInput(String input) {
        if (edtCategory.isFocused()==true){
            edtCategory.setText(input);
        }else if (edtBy.isFocused()==true){
            edtBy.setText(input);
        }
    }
}