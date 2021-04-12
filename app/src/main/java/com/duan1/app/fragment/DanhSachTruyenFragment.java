package com.duan1.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    private ListView lvDoctruyen;
    private DBHelper dbHelper = new DBHelper(getActivity());
    private List<Truyen> objTruyens;

    public class DoctruyenAdapter extends BaseAdapter {
        private Context context;
        private List<Truyen> truyenList;
        private ImageView imageView;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;

        public DoctruyenAdapter(Context context, List<Truyen> truyenList) {
            this.context = context;
            this.truyenList = truyenList;
        }


        @Override
        public int getCount() {
            return truyenList.size();
        }

        @Override
        public Truyen getItem(int position) {
            return truyenList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_truyen, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.imgAnh);
            textView3 = (TextView) convertView.findViewById(R.id.tvTentruyen);
            textView4 = (TextView) convertView.findViewById(R.id.tvTacgia);
            textView5 = (TextView) convertView.findViewById(R.id.tvTheloai);
            textView3.setText(truyenList.get(position).getTen());
            textView4.setText(truyenList.get(position).getTacGia());
            imageView.setImageBitmap(truyenList.get(position).getBitmap());
            return convertView;

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach_truyen, container, false);
        initView(view);
        objTruyens = new ArrayList<>();
        objTruyens = dbHelper.showAllTruyen(getActivity());
        System.out.println(objTruyens.size());
        DoctruyenAdapter adapter = new DoctruyenAdapter(getActivity(), objTruyens);
        lvDoctruyen.setAdapter(adapter);
        lvDoctruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoTaTruyenFragment(objTruyens.get(position))).addToBackStack(null).commit();
            }
        });

        return view;
    }

    private void initView(View view) {
        editTextTextPersonName2 = (EditText) view.findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = (EditText) view.findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = (EditText) view.findViewById(R.id.editTextTextPersonName4);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        textView = (TextView) view.findViewById(R.id.textView);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        lvDoctruyen = (ListView) view.findViewById(R.id.lvDoctruyen);
    }
}