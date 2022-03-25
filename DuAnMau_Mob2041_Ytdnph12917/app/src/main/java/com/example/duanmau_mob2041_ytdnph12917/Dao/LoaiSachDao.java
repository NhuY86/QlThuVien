package com.example.duanmau_mob2041_ytdnph12917.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_mob2041_ytdnph12917.DataBase.CreateDatabase;
import com.example.duanmau_mob2041_ytdnph12917.Model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDao {
    SQLiteDatabase sqlite;
    CreateDatabase createData;
    public LoaiSachDao(Context context) {
        createData = new CreateDatabase(context);
        sqlite = createData.getWritableDatabase();
    }
    public long ADDLS(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put(LoaiSach.COL_NAME_TEN_LS, loaiSach.getTenLS());
        return sqlite.insert(LoaiSach.TB_NAME, null, values);
    }

    public int UPDATELS(LoaiSach loaiSach) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiSach.COL_NAME_TEN_LS, loaiSach.getTenLS());
        return sqlite.update(LoaiSach.TB_NAME, contentValues, "maLoai=?", new String[]{String.valueOf(loaiSach.getMaLS())});
    }

    public int DELETELS(LoaiSach loaiSach) {
        return sqlite.delete(LoaiSach.TB_NAME, "maLoai=?", new String[]{String.valueOf(loaiSach.getMaLS())});
    }

    public List<LoaiSach> GETLS() {
        String dl = "SELECT * FROM LoaiSach";
        List<LoaiSach> list = getdata(dl);
        return list;
    }
    public LoaiSach getId(String id) {
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getdata(sql, id);
        return list.get(0);
    }

    private List<LoaiSach> getdata(String dl, String... Arays) {
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLS(Integer.parseInt(cursor.getString(cursor.getColumnIndex(LoaiSach.COL_NAME_MA_LS))));
            loaiSach.setTenLS(cursor.getString(cursor.getColumnIndex(LoaiSach.COL_NAME_TEN_LS)));
            list.add(loaiSach);
        }
        return list;

    }

}
