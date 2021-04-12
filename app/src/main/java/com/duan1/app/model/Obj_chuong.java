package com.duan1.app.model;

public class Obj_chuong {
    int chuong;
    String truyen,tenChuong,noiDung;

    public Obj_chuong(int chuong, String truyen, String tenChuong, String noiDung) {
        this.chuong = chuong;
        this.truyen = truyen;
        this.tenChuong = tenChuong;
        this.noiDung = noiDung;
    }

    public int getChuong() {
        return chuong;
    }

    public void setChuong(int chuong) {
        this.chuong = chuong;
    }

    public String getTruyen() {
        return truyen;
    }

    public void setTruyen(String truyen) {
        this.truyen = truyen;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
