package com.duan1.app.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.duan1.app.R;
import com.duan1.app.database.DBHelper;
import com.duan1.app.model.Chuong;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DocTruyenFragment extends Fragment implements View.OnClickListener {
    private TextView tvNoiDung, tvTenChuong;
    private FloatingActionButton fabNext, fabPre;
    private List<Chuong> chuongList;
    private ObservableScrollView scrollView;
    private int pos;
    private TextToSpeech t1;
    private Button button;
    public DBHelper dbHelper;
    private boolean isplaying = false;


    public DocTruyenFragment(List<Chuong> chuongList, int pos) {
        this.chuongList = chuongList;
        this.pos = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_truyen, container, false);
        initView(view);
        tvNoiDung.setText(chuongList.get(pos).getNoiDung());
        tvTenChuong.setText(chuongList.get(pos).getTenChuong());
        fabVisible();
        fabNext.setOnClickListener(this);
        fabPre.setOnClickListener(this);
        dbHelper=new DBHelper(getContext());

        t1 = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        t1.setLanguage(Locale.forLanguageTag("vi-VN"));
                    }
                }
            }
        });
        button = view.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
                if (isplaying) {
                    onPause();
                } else {
                    play();
                }
                isplaying = !isplaying;
            }
        });
        return view;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabNext:
                if (pos < chuongList.size() - 1) {
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_to_left_enter, R.anim.right_to_left_exit,
                                    R.anim.left_to_right_enter, R.anim.left_to_right_exit)
                            .replace(R.id.fragment_container, new DocTruyenFragment(chuongList, pos + 1))
                            .addToBackStack(null)
                            .commit();
                    dbHelper.insertLichSu(chuongList.get(pos +1),getContext());
                }
                break;
            case R.id.fabPre:
                if (pos > 0) {
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.left_to_right_enter, R.anim.left_to_right_exit
                                    , R.anim.right_to_left_enter, R.anim.right_to_left_exit)
                            .replace(R.id.fragment_container, new DocTruyenFragment(chuongList, pos - 1))
                            .addToBackStack(null)
                            .commit();
                }
                dbHelper.insertLichSu(chuongList.get(pos -1),getContext());
                break;
        }
    }

    private void initView(View view) {

        tvNoiDung = (TextView) view.findViewById(R.id.tvNoidung);
        tvTenChuong = (TextView) view.findViewById(R.id.tvTenChuong);
        fabNext = view.findViewById(R.id.fabNext);
        fabPre = view.findViewById(R.id.fabPre);
        scrollView = view.findViewById(R.id.scrollView);
    }

    private void fabVisible() {

        if (pos == 0) {
            fabPre.setVisibility(View.INVISIBLE);
        } else {
            fabPre.setVisibility(View.VISIBLE);
        }
        if (pos == chuongList.size() - 1) {
            fabNext.setVisibility(View.INVISIBLE);
        } else {
            fabNext.setVisibility(View.VISIBLE);
        }
    }

    private void play() {
        String textForReading = chuongList.get(pos).getNoiDung();
        //t1.speak(tospeak, TextToSpeech.QUEUE_FLUSH, null);
        //System.out.println(tospeak.length());
        int dividerLimit = 3900;
        if(textForReading.length() >= dividerLimit) {
            int textLength = textForReading.length();
            ArrayList<String> texts = new ArrayList<String>();
            int count = textLength / dividerLimit + ((textLength % dividerLimit == 0) ? 0 : 1);
            int start = 0;
            int end = textForReading.indexOf(" ", dividerLimit);
            for(int i = 1; i<=count; i++) {
                texts.add(textForReading.substring(start, end));
                start = end;
                if((start + dividerLimit) < textLength) {
                    end = textForReading.indexOf(" ", start + dividerLimit);
                } else {
                    end = textLength;
                }
            }
            for(int i=0; i<texts.size(); i++) {
                t1.speak(texts.get(i), TextToSpeech.QUEUE_ADD, null);
            }
        } else {
            t1.speak(textForReading, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void onPause() {
        if (t1 != null) {
            t1.stop();
        }
        super.onPause();
    }

}