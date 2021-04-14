package com.duan1.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.duan1.app.model.LichSu;
import com.duan1.app.model.Chuong;
import com.duan1.app.model.TheLoai;
import com.duan1.app.model.Truyen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DBHelper extends AppCompatActivity {
    public ArrayList<Truyen> truyenList = new ArrayList<>();
    public ArrayList<Truyen> truyenSearchList = new ArrayList<>();
    public ArrayList<Truyen> tacGiaSearchList = new ArrayList<>();
    public ArrayList<Truyen> theLoaiSearchList = new ArrayList<>();
    public ArrayList<TheLoai> theLoaiList = new ArrayList<>();
    public ArrayList<Chuong> chuongList = new ArrayList<>();
    public ArrayList<Truyen> searchBarList = new ArrayList<>();
    public ArrayList<Truyen> yeuThichList = new ArrayList<>();
    public ArrayList<LichSu> lichSuList = new ArrayList<>();
    Context context;

    public DBHelper(Context context) {
        this.context = context;
    }

    SQLiteDatabase sqLiteDatabase = null;
    String DB_NAME = "sqlite.db";
    String DB_PATH = "/databases/";

    public void addDB() {
        File file = context.getDatabasePath(DB_NAME);
        if (file.exists()) {

        } else {
            file.delete();
            copyDB();
        }
    }

    void copyDB() {
        try {
            InputStream inputStream = context.getAssets().open(DB_NAME);
            String outFileName = context.getApplicationInfo().dataDir + DB_PATH + DB_NAME;
            File f = new File(context.getApplicationInfo().dataDir + DB_PATH);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int len;
            while (((len = inputStream.read(buffer)) > 0)) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            Log.e("Lỗi xịt", "vcc");
        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }
    }

    public ArrayList<Truyen> showAllTruyen(Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("table_truyen", null, null, null, null, null, null);
        truyenList.clear();
        while (cursor.moveToNext()) {
            String ten = cursor.getString(0);
            String tacGia = cursor.getString(1);
            String theLoai = cursor.getString(2);
            String moTa = cursor.getString(3);
            int yeuThich = cursor.getInt(4);
            String image = cursor.getString(5);
            truyenList.add(new Truyen(ten, tacGia, theLoai, moTa, yeuThich, image));
        }
        cursor.close();
        return truyenList;
    }

    public ArrayList<Truyen> searchByTruyen(String truyen, Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("table_truyen", new String[]{}, "ten like '%" + truyen + "%'", null, null, null, null);
        truyenSearchList.clear();
        while (cursor.moveToNext()) {
            String ten = cursor.getString(0);
            String tacGia = cursor.getString(1);
            String theLoai = cursor.getString(2);
            String moTa = cursor.getString(3);
            int yeuThich = cursor.getInt(4);
            String image = cursor.getString(5);
            truyenSearchList.add(new Truyen(ten, tacGia, theLoai, moTa, yeuThich,image));
        }
        cursor.close();
        return truyenSearchList;
    }

    public ArrayList<Truyen> searchByTacGia(String tacGia, Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        //Cursor cursor=sqLiteDatabase.query("table_truyen",null,"tacgia =?",new String[]{tacGia},null,null,null);
        Cursor cursor = sqLiteDatabase.query("table_truyen", new String[]{}, "tacgia like '%" + tacGia + "%'", null, null, null, null);
        tacGiaSearchList.clear();
        while (cursor.moveToNext()) {
            String ten = cursor.getString(0);
            String tacgia = cursor.getString(1);
            String theLoai = cursor.getString(2);
            String moTa = cursor.getString(3);
            int yeuThich = cursor.getInt(4);
            String image = cursor.getString(5);
            tacGiaSearchList.add(new Truyen(ten, tacgia, theLoai, moTa, yeuThich,image));
        }
        cursor.close();
        return tacGiaSearchList;
    }

    public ArrayList<Truyen> searchByTheLoai(String theLoai, Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        //Cursor cursor=sqLiteDatabase.query("table_truyen",null,"theloai =?",new String[]{theLoai},null,null,null);
        Cursor cursor = sqLiteDatabase.query("table_truyen", new String[]{}, "theloai like '%" + theLoai + "%'", null, null, null, null);
        theLoaiSearchList.clear();
        while (cursor.moveToNext()) {
            String ten = cursor.getString(0);
            String tacgia = cursor.getString(1);
            String theloai = cursor.getString(2);
            String moTa = cursor.getString(3);
            int yeuThich = cursor.getInt(4);
            String image=cursor.getString(5);
            theLoaiSearchList.add(new Truyen(ten, tacgia, theloai, moTa, yeuThich,image));
        }
        cursor.close();
        return theLoaiSearchList;
    }

    public ArrayList<TheLoai> showAllTheLoaiList(Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("table_the_loai", null, null, null, null, null, null);
        theLoaiList.clear();
        while (cursor.moveToNext()) {
            String theLoai = cursor.getString(0);
            theLoaiList.add(new TheLoai(theLoai));
        }
        cursor.close();
        return theLoaiList;
    }

    public ArrayList<Chuong> showAllChuongList(String truyen, Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("table_chuong", null, "truyen=?", new String[]{truyen}, null, null, null);
        chuongList.clear();
        while (cursor.moveToNext()) {
            int chuong = cursor.getInt(0);
            String truyenSearch = cursor.getString(1);
            String tenChuong = cursor.getString(2);
            String noiDung = cursor.getString(3);
            chuongList.add(new Chuong(chuong, truyenSearch, tenChuong, noiDung));
        }
        cursor.close();
        return chuongList;
    }

    public ArrayList<Truyen> showAllYeuThichList(Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("table_truyen", null, "yeuthich=?", new String[]{"1"}, null, null, null);
        yeuThichList.clear();
        while (cursor.moveToNext()) {
            String ten = cursor.getString(0);
            String tacgia = cursor.getString(1);
            String theloai = cursor.getString(2);
            String moTa = cursor.getString(3);
            int yeuThich = cursor.getInt(4);
            String image=cursor.getString(5);
            yeuThichList.add(new Truyen(ten, tacgia, theloai, moTa, yeuThich,image));
        }
        cursor.close();
        return yeuThichList;
    }

    public ArrayList<Truyen> updateTruyenYeuThich(String truyen, Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("table_truyen", null, "ten=?", new String[]{truyen}, null, null, null);
        cursor.moveToFirst();
        String ten = cursor.getString(0);
        String tacgia = cursor.getString(1);
        String theloai = cursor.getString(2);
        String moTa = cursor.getString(3);
        int yeuThich = cursor.getInt(4);
            String image=cursor.getString(5);
        Truyen obj_truyen = new Truyen(ten, tacgia, theloai, moTa, yeuThich,image);
        if (yeuThich == 0) {
            ContentValues values = new ContentValues();
            values.put("yeuthich", 1);
            sqLiteDatabase.update("table_truyen", values, "ten=?", new String[]{truyen});
        } else if (yeuThich == 1) {
            ContentValues values = new ContentValues();
            values.put("yeuthich", 0);
            sqLiteDatabase.update("table_truyen", values, "ten=?", new String[]{truyen});
        }

        cursor.close();
        return null;
    }

    public ArrayList<Truyen> searchBySearchBar(String timKiem, String theo, String theLoai, Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        switch (theo) {
            case "Tên Truyện":
                for (int i = 0; i < searchByTruyen(timKiem, context).size(); i++) {
                    Truyen obj_truyen = searchByTruyen(timKiem, context).get(i);
                    for (int j = 0; j < searchByTheLoai(theLoai, context).size(); j++) {
                        Truyen obj_truyen1 = searchByTheLoai(theLoai, context).get(j);
                        if (obj_truyen.getTen().equals(obj_truyen1.getTen())) {
                            searchBarList.add(obj_truyen);
                        }
                    }
                }
                break;
            case "Tác Giả":
                for (int i = 0; i < searchByTacGia(timKiem, context).size(); i++) {
                    Truyen obj_truyen = searchByTacGia(timKiem, context).get(i);
                    for (int j = 0; j < searchByTheLoai(theLoai, context).size(); j++) {
                        Truyen obj_truyen1 = searchByTheLoai(theLoai, context).get(j);
                        if (obj_truyen.getTen().equals(obj_truyen1.getTen())) {
                            searchBarList.add(obj_truyen);
                            break;
                        }
                    }
                }
                break;
        }
        return searchBarList;
    }

    public void insertLichSu(Chuong obj_chuong, Context context) {
        Date date = null;
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("truyen", obj_chuong.getTruyen());
        values.put("chuong", obj_chuong.getChuong());
        values.put("datetime", getDateTime());
        long a = sqLiteDatabase.insert("table_lich_su", null, values);
        System.out.println("addlichsu " + a);
    }

    public void deleteLichSu(LichSu obj_lich_su, Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("truyen", obj_lich_su.getTen());
        values.put("chuong", obj_lich_su.getChuong());
        long a = sqLiteDatabase.delete("table_lich_su", "truyen='" + obj_lich_su.getTen() + "' and chuong=" + obj_lich_su.getChuong() + " and datetime= '" + obj_lich_su.getTime() + "'", new String[]{});
        System.out.println("dellichsu " + a);
    }

    public ArrayList<LichSu> showLichSuList(Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("table_lich_su", null, null, null, null, null, null);
        lichSuList.clear();
        while (cursor.moveToNext()) {
            String truyen = cursor.getString(0);
            String time = cursor.getString(2);
            int chuong = cursor.getInt(1);
            lichSuList.add(new LichSu(truyen, chuong, time));
        }
        cursor.close();
        return lichSuList;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
