package com.duan1.app;

public class Obj_Lich_Su {
    public Obj_Lich_Su(String ten, int chuong,String time) {
        this.ten = ten;
        this.chuong = chuong;
        this.time=time;
    }

    String ten;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;
    int chuong;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getChuong() {
        return chuong;
    }

    public void setChuong(int chuong) {
        this.chuong = chuong;
    }
}
