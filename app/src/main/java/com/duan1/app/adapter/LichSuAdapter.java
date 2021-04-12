package com.duan1.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.app.R;
import com.duan1.app.model.Truyen;

import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.LichSuHolder> {
    private Context context;
    private List<Truyen> truyenList;


    public LichSuAdapter(Context context, List<Truyen> truyenList) {
        this.context = context;
        this.truyenList = truyenList;
    }
    @NonNull
    @Override
    public LichSuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_truyen, parent, false);
        return new LichSuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuHolder holder, int position) {
        holder.textView3.setText(truyenList.get(position).getTen());
        holder.textView4.setText(truyenList.get(position).getTacGia());
        holder.imageView.setImageBitmap(truyenList.get(position).getBitmap());
    }

    @Override
    public int getItemCount() {
        return truyenList.size();
    }

    public static class LichSuHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;
        public LichSuHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgAnh);
            textView3 = (TextView) itemView.findViewById(R.id.tvTentruyen);
            textView4 = (TextView) itemView.findViewById(R.id.tvTacgia);
            textView5 = (TextView) itemView.findViewById(R.id.tvTheloai);
        }
    }
}
