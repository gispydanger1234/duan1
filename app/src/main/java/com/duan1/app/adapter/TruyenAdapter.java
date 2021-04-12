package com.duan1.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.app.R;
import com.duan1.app.model.Truyen;

import java.util.List;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.TruyenHolder> {
    private Context context;
    private List<Truyen> truyenList;
    private OnClick onClick;


    public TruyenAdapter(Context context, List<Truyen> truyenList, OnClick onClick) {
        this.context = context;
        this.truyenList = truyenList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public TruyenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_truyen, parent, false);
        return new TruyenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenHolder holder, final int position) {
        holder.textView3.setText(truyenList.get(position).getTen());
        holder.imageView.setImageBitmap(truyenList.get(position).getBitmap());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return truyenList.size();
    }

    public static class TruyenHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView3;

        public TruyenHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgAnh);
            textView3 = (TextView) itemView.findViewById(R.id.tvTentruyen);
        }
    }

    public interface OnClick {
        void onclick(int pos);
    }
}