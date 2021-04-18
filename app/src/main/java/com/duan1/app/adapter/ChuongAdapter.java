package com.duan1.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duan1.app.R;
import com.duan1.app.model.Chuong;

import java.util.List;

public class ChuongAdapter extends BaseAdapter {
    private Context context;
    private List<Chuong> chuongList;
    private String truyen;
    private TextView tvChuong;

    public ChuongAdapter(Context context, List<Chuong> chuongList) {
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
        tvChuong.setText(chuongList.get(position).getTruyen()+" Chương "+chuongList.get(position).getChuong());
        return convertView;
    }
}
