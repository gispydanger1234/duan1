package com.duan1.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import database.DBHelper;

public class DetailTruyenFragment extends Fragment {

    private ImageView imgAnh;
    private TextView tvTacgia;
    private TextView tvTheloai;
    private TextView tvTentruyen;
    private ImageView imgYeuthich;
    private TextView tvTomtat;
    private ListView lvChuong;
    private DBHelper dbHelper = new DBHelper(getActivity());
    private List<Obj_chuong> obj_chuongs;
    public String tenTruyen;

    public DetailTruyenFragment(String truyen) {
        this.tenTruyen = truyen;
    }

    public class ChuongAdapter extends BaseAdapter {
        private Context context;
        private List<Obj_chuong> chuongList;
        private String truyen;
        private TextView tvChuong;

        public ChuongAdapter(Context context, List<Obj_chuong> chuongList) {
            this.context = context;
            this.chuongList = chuongList;
        }

        @Override
        public int getCount() {
            return chuongList.size();
        }

        @Override
        public Object getItem(int position) {
            return chuongList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chuong, parent, false);
            tvChuong = (TextView) convertView.findViewById(R.id.tvChuong);
            tvChuong.setText(chuongList.get(position).getChuong()+"  "+chuongList.get(position).getTenChuong());
            return convertView;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_truyen, container, false);
        initView(view);
        obj_chuongs = new ArrayList<>();
        obj_chuongs = dbHelper.showAllChuongList(tenTruyen,getActivity());
        ChuongAdapter chuongAdapter = new ChuongAdapter(getActivity(),obj_chuongs);
        lvChuong.setAdapter(chuongAdapter);
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