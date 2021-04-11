package com.duan1.app;

public class Obj_truyen {
    public String ten,tacGia,theLoai,moTa;
    public int yeuThich;

    public Obj_truyen(String ten, String tacGia, String theLoai, String moTa, int yeuThich) {
        this.ten = ten;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.moTa = moTa;
        this.yeuThich = yeuThich;
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
}
