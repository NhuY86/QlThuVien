package com.example.duanmau_mob2041_ytdnph12917.Model;

public class PhieuMuon {
    int maPM;
    String maNVpm;
    int maTVpm;
    int maSpm;
    int tienthue;
    String ngaymuon;
    int trasach;

    public static final String TB_NAME_PM = "PhieuMuon";
    public static final String COL_NAME_MA_PM = "maPM";
    public static final String COL_NAME_MA_NV_PM = "maNV";
    public static final String COL_NAME_MA_TV_PM = "maTV";
    public static final String COL_NAME_MA_S_PM = "maSach";
    public static final String COL_NAME_TIEN_THUE = "tienThue";
    public static final String COL_NAME_NGAY_MUON = "ngay";
    public static final String COL_NAME_TRA_SACH = "traSach";

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, String maNVpm, int maTVpm, int maSpm, int tienthue, String ngaymuon, int trasach) {
        this.maPM = maPM;
        this.maNVpm = maNVpm;
        this.maTVpm = maTVpm;
        this.maSpm = maSpm;
        this.tienthue = tienthue;
        this.ngaymuon = ngaymuon;
        this.trasach = trasach;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaNVpm() {
        return maNVpm;
    }

    public void setMaNVpm(String maNVpm) {
        this.maNVpm = maNVpm;
    }

    public int getMaTVpm() {
        return maTVpm;
    }

    public void setMaTVpm(int maTVpm) {
        this.maTVpm = maTVpm;
    }

    public int getMaSpm() {
        return maSpm;
    }

    public void setMaSpm(int maSpm) {
        this.maSpm = maSpm;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }
}
