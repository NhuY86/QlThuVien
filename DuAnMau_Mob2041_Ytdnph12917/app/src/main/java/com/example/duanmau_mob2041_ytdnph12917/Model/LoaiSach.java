package com.example.duanmau_mob2041_ytdnph12917.Model;

public class LoaiSach {
    private  int maLS ;
    private  String tenLS;

    public static final String TB_NAME = "LoaiSach";
    public static final String COL_NAME_MA_LS ="maLoai";
    public static final String COL_NAME_TEN_LS="tenLoai";

    public LoaiSach() {
    }

    public LoaiSach(int maLS, String tenLS) {
        this.maLS = maLS;
        this.tenLS = tenLS;
    }

    public int getMaLS() {
        return maLS;
    }

    public void setMaLS(int maLS) {
        this.maLS = maLS;
    }

    public String getTenLS() {
        return tenLS;
    }

    public void setTenLS(String tenLS) {
        this.tenLS = tenLS;
    }
}
