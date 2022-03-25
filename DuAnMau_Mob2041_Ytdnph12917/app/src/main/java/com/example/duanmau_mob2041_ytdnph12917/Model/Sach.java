package com.example.duanmau_mob2041_ytdnph12917.Model;

public class Sach {
    int mas;
    String tens;
    int gias;
    int mals;
    String  km;
    public static final String TB_NAME = "Sach";
    public static final String COL_TB_KM = "km";
    public static final String COL_NAME_MA_SACH = "maSach";
    public static final String COL_NAME_TEN_SACH = "tenSach";
    public static final String COL_NAME_GIA_SACH = "giaThue";
    public static final String COL_NAME_MAL_SACH = "maLoai";

    public Sach() {
    }

    public Sach(int mas, String tens, int gias, int mals) {
        this.mas = mas;
        this.tens = tens;
        this.gias = gias;
        this.mals = mals;
        this.km = km;
    }

    public int getMas() {
        return mas;
    }

    public void setMas(int mas) {
        this.mas = mas;
    }

    public String getTens() {
        return tens;
    }

    public void setTens(String tens) {
        this.tens = tens;
    }

    public int getGias() {
        return gias;
    }

    public void setGias(int gias) {
        this.gias = gias;
    }

    public int getMals() {
        return mals;
    }

    public void setMals(int mals) {
        this.mals = mals;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }
}
