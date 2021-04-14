package com.duan1.app.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.duan1.app.R;
import com.duan1.app.database.DBHelper;

import java.util.ArrayList;

public class DialogTheLoai extends DialogFragment {
    ListView listView;
    DBHelper dbHelper;
    Context context;

    public interface SendTheLoai{
        void sendInput(String input);
    }
    public SendTheLoai sendTheLoai;

    public DialogTheLoai(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_the_loai, container, false);
        listView=view.findViewById(R.id.lvDialogTheLoai);
        dbHelper=new DBHelper(context);
        final ArrayList <String> values=new ArrayList<>();
        for (int i = 0; i < dbHelper.showAllTheLoaiList(context).size() ; i++) {
            values.add(dbHelper.showAllTheLoaiList(context).get(i).getTen());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,android.R.id.text1,values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendTheLoai.sendInput(values.get(position).toString());
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            sendTheLoai = (DialogTheLoai.SendTheLoai) getTargetFragment();
        }catch (ClassCastException e){
            Log.e("e", "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}
