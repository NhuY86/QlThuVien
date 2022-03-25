package com.example.duanmau_mob2041_ytdnph12917.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_mob2041_ytdnph12917.DataBase.CreateDatabase;
import com.example.duanmau_mob2041_ytdnph12917.Model.PhieuMuon;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDao {
    SQLiteDatabase sqlite;
    CreateDatabase createData;
    public PhieuMuonDao(Context context) {
        createData = new CreateDatabase(context);
        sqlite = createData.getWritableDatabase();
    }
    public long ADDPM(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put(PhieuMuon.COL_NAME_MA_TV_PM, phieuMuon.getMaTVpm());
        values.put(PhieuMuon.COL_NAME_MA_NV_PM, phieuMuon.getMaNVpm());
        values.put(PhieuMuon.COL_NAME_MA_S_PM, phieuMuon.getMaSpm());
        values.put(PhieuMuon.COL_NAME_NGAY_MUON, phieuMuon.getNgaymuon());
        values.put(PhieuMuon.COL_NAME_TIEN_THUE, phieuMuon.getTienthue());
        values.put(PhieuMuon.COL_NAME_TRA_SACH, phieuMuon.getTrasach());
        return sqlite.insert("PhieuMuon", null, values);
    }

    public int DELETEPM(PhieuMuon phieuMuon) {
        return sqlite.delete(PhieuMuon.TB_NAME_PM, "maPM=?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    public int UPDATEPM(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put(PhieuMuon.COL_NAME_MA_TV_PM, phieuMuon.getMaTVpm());
        values.put(PhieuMuon.COL_NAME_MA_NV_PM, phieuMuon.getMaNVpm());
        values.put(PhieuMuon.COL_NAME_MA_S_PM, phieuMuon.getMaSpm());
        values.put(PhieuMuon.COL_NAME_NGAY_MUON, phieuMuon.getNgaymuon());
        values.put(PhieuMuon.COL_NAME_TIEN_THUE, phieuMuon.getTienthue());
        values.put(PhieuMuon.COL_NAME_TRA_SACH, phieuMuon.getTrasach());
        return sqlite.update(PhieuMuon.TB_NAME_PM, values, "maPM=?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    //Lấy Phần tử Phiếu Mượn từ id cua PM trog Csdl
    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    public List<PhieuMuon> GETPM() {
        String sql = "SELECT * FROM PhieuMuon";
        List<PhieuMuon> list = getData(sql);
        return list;
    }

    // getData viet kieu List để sd nhiều lần
    private List<PhieuMuon> getData(String sql, String... Arays) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery(sql, Arays);
        while (cursor.moveToNext()) {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPM(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_MA_PM))));
            phieuMuon.setMaNVpm(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_MA_NV_PM)));
            phieuMuon.setMaTVpm(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_MA_TV_PM))));
            phieuMuon.setMaSpm(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_MA_S_PM))));
            phieuMuon.setNgaymuon(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_NGAY_MUON)));
            phieuMuon.setTienthue(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_TIEN_THUE))));
            phieuMuon.setTrasach(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_TRA_SACH))));
            list.add(phieuMuon);
        }
        return list;

    }

    public int laygiatritheongay(String tungay, String dengay) {
        Cursor cursor = sqlite.rawQuery("SELECT SUM(tienThue) FROM PhieuMuon WHERE ngay >='"+ tungay + "'AND ngay <='" + dengay + "'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
}
