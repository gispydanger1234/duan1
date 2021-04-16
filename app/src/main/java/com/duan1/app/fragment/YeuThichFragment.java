package com.duan1.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duan1.app.R;
import com.duan1.app.adapter.LichSuAdapter;
import com.duan1.app.adapter.TruyenAdapter;
import com.duan1.app.database.DBHelper;


public class YeuThichFragment extends Fragment {
    DBHelper dbHelper=new DBHelper(getContext());
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_yeu_thich, container, false);
        initView(view);
        TruyenAdapter truyenAdapter=new TruyenAdapter(getContext(), dbHelper.showAllYeuThichList(getContext()), new TruyenAdapter.TruyenOnClick() {
            @Override
            public void onclick(int pos) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoTaTruyenFragment(dbHelper.showAllYeuThichList(getContext()).get(pos))).addToBackStack(null).commit();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(truyenAdapter);
        return view;

    }
    private void initView(View view) {
        recyclerView=view.findViewById(R.id.rvYeuThich);
    }
}