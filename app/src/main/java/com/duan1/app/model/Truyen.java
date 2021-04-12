package com.duan1.app.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Truyen {
    private String ten,tacGia,theLoai,moTa;
    private String image;
    private int yeuThich;


    public Truyen(String ten, String tacGia, String theLoai, String moTa, int yeuThich, String image ) {
        this.ten = ten;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.moTa = moTa;
        this.yeuThich = yeuThich;
        this.image = image;
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

    public void setYeuThich(int yeuThich) {
        this.yeuThich = yeuThich;
    }

    public Bitmap getBitmap(){
        byte[] imageBytes = Base64.decode(this.image,Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.length);
        return decodedByte;
    }
}
