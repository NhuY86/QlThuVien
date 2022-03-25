package com.example.duanmau_mob2041_ytdnph12917.Model;

public class ThanhVien {
    int IDTV;
    String HoTenTV;
    String NamsinhTV;

    public static final String TB_NAME = "ThanhVien";
    public static final String COL_NAME_ID = "maTV";
    public static final String COL_NAME_HO_TEN = "hoTen";
    public static final String COL_NAME_NAM_SINH = "namSinh";

    public ThanhVien() {
    }

    public ThanhVien(int IDTV, String hoTenTV, String namsinhTV) {
        this.IDTV = IDTV;
        HoTenTV = hoTenTV;
        NamsinhTV = namsinhTV;
    }

    public int getIDTV() {
        return IDTV;
    }

    public void setIDTV(int IDTV) {
        this.IDTV = IDTV;
    }

    public String getHoTenTV() {
        return HoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        HoTenTV = hoTenTV;
    }

    public String getNamsinhTV() {
        return NamsinhTV;
    }

    public void setNamsinhTV(String namsinhTV) {
        NamsinhTV = namsinhTV;
    }
}
