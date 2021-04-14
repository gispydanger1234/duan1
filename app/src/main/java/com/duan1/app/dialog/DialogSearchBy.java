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

public class DialogSearchBy extends DialogFragment {
    ListView listView;
    DBHelper dbHelper;
    Context context;

    public interface SendSearchBy{
        void sendInput(String input);
    }
    public SendSearchBy sendSearchBy;

    public DialogSearchBy(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_search_by, container, false);
        listView=view.findViewById(R.id.lvdialog);
        dbHelper=new DBHelper(context);
        final String[] values = new String[] { "Tác Giả","Tên Truyện"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,android.R.id.text1,values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        sendSearchBy.sendInput("Tác Giả");
                        break;
                    case 1:
                        sendSearchBy.sendInput("Tên Truyện");
                        System.out.println(dbHelper.showAllTheLoaiList(getActivity()).size());
                        break;
                }

            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            sendSearchBy = (SendSearchBy) getTargetFragment();
        }catch (ClassCastException e){
            Log.e("e", "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}
