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
import com.duan1.app.database.DBHelper;
import com.duan1.app.model.LichSu;
import com.duan1.app.model.Truyen;

import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.LichSuHolder> {
    private Context context;
    private List<LichSu> lichSuList;
    private LichSuOnClick onClick;
    public DBHelper dbHelper=new DBHelper(context);


    public LichSuAdapter(Context ccontext, List<LichSu> lichSuList, LichSuOnClick onClick) {
        this.context = ccontext;
        this.lichSuList = lichSuList;
        this.onClick=onClick;
    }
    @NonNull
    @Override
    public LichSuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lich_su, parent, false);
        return new LichSuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuHolder holder, final int position) {
        holder.tvLichsu.setText(lichSuList.get(position).getTen()+" chương "+lichSuList.get(position).getChuong());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onclick(position);
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteLichSu(lichSuList.get(position),context);
                notifyDataSetChanged();
                updateList(dbHelper.showLichSuList(context));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lichSuList.size();
    }

    public static class LichSuHolder extends RecyclerView.ViewHolder {
        private ImageView imgXoa;
        private TextView tvLichsu;

        public LichSuHolder(@NonNull View itemView) {
            super(itemView);
            imgXoa = (ImageView) itemView.findViewById(R.id.imgXoa);
            tvLichsu = (TextView) itemView.findViewById(R.id.tvLichsu);
        }
    }
    public interface LichSuOnClick {
        void onclick(int pos);
    }
    public void updateList(List<LichSu> todolist){
        this.lichSuList = todolist;
        notifyDataSetChanged();
    }
}
