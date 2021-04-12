package com.duan1.app.model;

public class LichSu {

    private String ten;
    private String time;
    private int chuong;

    public LichSu(String ten, int chuong, String time) {
        this.ten = ten;
        this.chuong = chuong;
        this.time = time;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getChuong() {
        return chuong;
    }

    public void setChuong(int chuong) {
        this.chuong = chuong;
    }
}
