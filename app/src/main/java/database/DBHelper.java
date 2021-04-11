package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.duan1.app.Obj_Lich_Su;
import com.duan1.app.Obj_chuong;
import com.duan1.app.Obj_the_loai;
import com.duan1.app.Obj_truyen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DBHelper extends AppCompatActivity {
    public ArrayList<Obj_truyen> truyenList=new ArrayList<>();
    public ArrayList<Obj_truyen> truyenSearchList=new ArrayList<>();
    public ArrayList<Obj_truyen> tacGiaSearchList=new ArrayList<>();
    public ArrayList<Obj_truyen> theLoaiSearchList=new ArrayList<>();
    public ArrayList<Obj_the_loai> theLoaiList=new ArrayList<>();
    public ArrayList<Obj_chuong> chuongList=new ArrayList<>();
    public ArrayList<Obj_truyen> searchBarList=new ArrayList<>();
    public ArrayList<Obj_truyen> yeuThichList=new ArrayList<>();
    public ArrayList<Obj_Lich_Su> lichSuList=new ArrayList<>();
    Context context;
    public DBHelper(Context context){
        this.context=context;
    }
    SQLiteDatabase sqLiteDatabase=null;
    String DB_NAME="sqlite.db";
    String DB_PATH="/databases/";
    public void addDB(){
        File file=context.getDatabasePath(DB_NAME);
        if(file.exists()){

        }else {
            file.delete();
            copyDB();
        }
    }
    void copyDB(){
        try {
            InputStream inputStream=context.getAssets().open(DB_NAME);
            String outFileName=context.getApplicationInfo().dataDir+DB_PATH+DB_NAME;
            File f=new File(context.getApplicationInfo().dataDir+DB_PATH);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[] buffer=new byte[1024];
            int len;
            while (((len=inputStream.read(buffer))>0)){
                outputStream.write(buffer,0,len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            Log.e("Lỗi xịt","vcc");
        }catch (Exception e){
            Log.e("Lỗi",e.toString());
        }
    }

    public ArrayList<Obj_truyen> showAllTruyen(Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.query("table_truyen",null,null,null,null,null,null);
        truyenList.clear();
        while (cursor.moveToNext()){
                String ten=cursor.getString(0);
                String tacGia=cursor.getString(1);
                String theLoai=cursor.getString(2);
                String moTa =cursor.getString(3);
                int yeuThich=cursor.getInt(4);
                String blob=cursor.getString(5);
                truyenList.add(new Obj_truyen(ten,tacGia,theLoai,moTa,yeuThich,blob));
        }
        cursor.close();
        return truyenList;
    }
    public ArrayList<Obj_truyen> searchByTruyen(String truyen,Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        //Cursor cursor=sqLiteDatabase.query("table_truyen",null,"ten =?",new String[]{truyen},null,null,null);
        Cursor cursor=sqLiteDatabase.query("table_truyen",new String[]{},"ten like '%"+truyen+"%'",null,null,null,null);
        truyenSearchList.clear();
        while (cursor.moveToNext()){
            String ten=cursor.getString(0);
            String tacGia=cursor.getString(1);
            String theLoai=cursor.getString(2);
            String moTa =cursor.getString(3);
            int yeuThich=cursor.getInt(4);
            String blob=cursor.getString(5);
            truyenSearchList.add(new Obj_truyen(ten,tacGia,theLoai,moTa,yeuThich,blob));
        }
        cursor.close();
        return truyenSearchList;
    }
    public ArrayList<Obj_truyen> searchByTacGia(String tacGia,Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        //Cursor cursor=sqLiteDatabase.query("table_truyen",null,"tacgia =?",new String[]{tacGia},null,null,null);
        Cursor cursor=sqLiteDatabase.query("table_truyen",new String[]{},"tacgia like '%"+tacGia+"%'",null,null,null,null);
        tacGiaSearchList.clear();
        while (cursor.moveToNext()){
            String ten=cursor.getString(0);
            String tacgia=cursor.getString(1);
            String theLoai=cursor.getString(2);
            String moTa =cursor.getString(3);
            int yeuThich=cursor.getInt(4);
            String blob=cursor.getString(5);
            tacGiaSearchList.add(new Obj_truyen(ten,tacgia,theLoai,moTa,yeuThich,blob));
        }
        cursor.close();
        return tacGiaSearchList;
    }
    public ArrayList<Obj_truyen> searchByTheLoai(String theLoai,Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        //Cursor cursor=sqLiteDatabase.query("table_truyen",null,"theloai =?",new String[]{theLoai},null,null,null);
        Cursor cursor=sqLiteDatabase.query("table_truyen",new String[]{},"theloai like '%"+theLoai+"%'",null,null,null,null);
        theLoaiSearchList.clear();
        while (cursor.moveToNext()){
            String ten=cursor.getString(0);
            String tacgia=cursor.getString(1);
            String theloai=cursor.getString(2);
            String moTa =cursor.getString(3);
            int yeuThich=cursor.getInt(4);
            String blob=cursor.getString(5);
            theLoaiSearchList.add(new Obj_truyen(ten,tacgia,theloai,moTa,yeuThich,blob));
        }
        cursor.close();
        return theLoaiSearchList;
    }
    public ArrayList<Obj_the_loai> theLoaiList(Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.query("table_the_loai",null,null,null,null,null,null);
        theLoaiList.clear();
        while (cursor.moveToNext()){
            String theLoai=cursor.getString(0);
            theLoaiList.add(new Obj_the_loai(theLoai));
        }
        cursor.close();
        return theLoaiList;
    }
    public ArrayList<Obj_chuong> showAllChuongList(String truyen,Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.query("table_chuong",null,"truyen=?",new String[]{truyen},null,null,null);
        chuongList.clear();
        while (cursor.moveToNext()){
            int chuong=cursor.getInt(0);
            String truyenSearch=cursor.getString(1);
            String tenChuong=cursor.getString(2);
            String noiDung =cursor.getString(3);
            chuongList.add(new Obj_chuong(chuong,truyenSearch,tenChuong,noiDung));
        }
        cursor.close();
        return chuongList;
    }
    public ArrayList<Obj_truyen> showAllYeuThichList(Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.query("table_truyen",null,"yeuthich=?",new String[]{"1"},null,null,null);
        yeuThichList.clear();
        while (cursor.moveToNext()){
            String ten=cursor.getString(0);
            String tacgia=cursor.getString(1);
            String theloai=cursor.getString(2);
            String moTa =cursor.getString(3);
            int yeuThich=cursor.getInt(4);
            String blob=cursor.getString(5);
            yeuThichList.add(new Obj_truyen(ten,tacgia,theloai,moTa,yeuThich,blob));
        }
        cursor.close();
        return yeuThichList;
    }
    public ArrayList<Obj_truyen> updateTruyenYeuThich(String truyen,Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.query("table_truyen",null,"ten=?",new String[]{truyen},null,null,null);
        cursor.moveToFirst();
            String ten=cursor.getString(0);
            String tacgia=cursor.getString(1);
            String theloai=cursor.getString(2);
            String moTa =cursor.getString(3);
            int yeuThich=cursor.getInt(4);
            String blob=cursor.getString(5);
            Obj_truyen obj_truyen=new Obj_truyen(ten,tacgia,theloai,moTa,yeuThich,blob);
            if (yeuThich==0){
                ContentValues values=new ContentValues();
                values.put("yeuthich",1);
                sqLiteDatabase.update("table_truyen",values,"ten=?",new String[]{truyen});
            }else if (yeuThich==1) {
                ContentValues values=new ContentValues();
                values.put("yeuthich",0);
                sqLiteDatabase.update("table_truyen",values,"ten=?",new String[]{truyen});
            }

        cursor.close();
        return null;
    }
    public ArrayList<Obj_truyen> searchBySearchBar(String timKiem, String theo, String theLoai,Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        switch (theo){
            case "Tên Truyện":
                for (int i = 0; i <searchByTruyen(timKiem,context).size() ; i++) {
                    Obj_truyen obj_truyen=searchByTruyen(timKiem,context).get(i);
                    for (int j = 0; j <searchByTheLoai(theLoai,context).size() ; j++) {
                        Obj_truyen obj_truyen1=searchByTheLoai(theLoai,context).get(j);
                        if (obj_truyen.getTen().equals(obj_truyen1.getTen())){
                            searchBarList.add(obj_truyen);
                        }
                    }
                }
                break;
            case "Tác Giả":
                for (int i = 0; i <searchByTacGia(timKiem,context).size() ; i++) {
                    Obj_truyen obj_truyen=searchByTacGia(timKiem,context).get(i);
                    for (int j = 0; j <searchByTheLoai(theLoai,context).size() ; j++) {
                        Obj_truyen obj_truyen1=searchByTheLoai(theLoai,context).get(j);
                        if (obj_truyen.getTen().equals(obj_truyen1.getTen())){
                            searchBarList.add(obj_truyen);
                            break;
                        }
                    }
                }
                break;
        }
        return searchBarList;
    }
    public void insertLichSu(Obj_chuong obj_chuong, Context context){
        Date date=null;
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        ContentValues values=new ContentValues();
        values.put("truyen",obj_chuong.getTruyen());
        values.put("chuong",obj_chuong.getChuong());
        values.put("datetime",getDateTime());
        long a=sqLiteDatabase.insert("table_lich_su",null,values);
        System.out.println("addlichsu "+a);
    }
    public void deleteLichSu(Obj_Lich_Su obj_lich_su, Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        ContentValues values=new ContentValues();
        values.put("truyen",obj_lich_su.getTen());
        values.put("chuong",obj_lich_su.getChuong());
        long a=sqLiteDatabase.delete("table_lich_su","truyen='"+obj_lich_su.getTen()+"' and chuong="+obj_lich_su.getChuong()+" and datetime= '"+obj_lich_su.getTime()+"'",new String[]{});
        System.out.println("dellichsu "+a);
    }
    public ArrayList<Obj_Lich_Su> showLichSuList(Context context){
        this.context=context;
        sqLiteDatabase=context.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.query("table_lich_su",null,null,null,null,null,null);
        lichSuList.clear();
        while (cursor.moveToNext()){
            String truyen=cursor.getString(0);
            String time=cursor.getString(2);
            int chuong=cursor.getInt(1);
            lichSuList.add(new Obj_Lich_Su(truyen,chuong,time));
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
