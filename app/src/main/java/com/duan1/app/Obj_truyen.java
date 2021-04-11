package com.duan1.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Obj_truyen {
    public String ten,tacGia,theLoai,moTa,blob;
    public int yeuThich;

    public Obj_truyen(String ten, String tacGia, String theLoai, String moTa, int yeuThich,String blob) {
        this.ten = ten;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.moTa = moTa;
        this.yeuThich = yeuThich;
        this.blob=blob;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getYeuThich() {
        return yeuThich;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }

    public void setYeuThich(int yeuThich) {
        this.yeuThich = yeuThich;
    }

    public Bitmap getBitmap(){
        byte[] imageBytes = Base64.decode(this.blob,Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.length);
        return decodedByte;
    }
}
