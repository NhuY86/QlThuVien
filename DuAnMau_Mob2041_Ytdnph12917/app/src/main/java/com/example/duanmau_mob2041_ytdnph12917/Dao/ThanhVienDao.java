package com.example.duanmau_mob2041_ytdnph12917.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_mob2041_ytdnph12917.DataBase.CreateDatabase;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao {
    SQLiteDatabase sqlite;
    CreateDatabase createData;
    public ThanhVienDao(Context context) {
        createData = new CreateDatabase(context);
        sqlite = createData.getWritableDatabase();
    }
    public long ADDTV(ThanhVien thanhVien) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThanhVien.COL_NAME_HO_TEN, thanhVien.getHoTenTV());
        contentValues.put(ThanhVien.COL_NAME_NAM_SINH, thanhVien.getNamsinhTV());
        return sqlite.insert(ThanhVien.TB_NAME, null, contentValues);
    }

    public int UPDATETV(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put(ThanhVien.COL_NAME_HO_TEN, thanhVien.getHoTenTV());
        values.put(ThanhVien.COL_NAME_NAM_SINH, thanhVien.getNamsinhTV());
        return sqlite.update(ThanhVien.TB_NAME, values, "maTV=?", new String[]{String.valueOf(thanhVien.getIDTV())});
    }

    public int DELETETV(ThanhVien thanhVien) {
        return sqlite.delete(ThanhVien.TB_NAME, "maTV=?", new String[]{String.valueOf(thanhVien.getIDTV())});
    }

    public List<ThanhVien> GETTV() {
        String dl = "SELECT * FROM ThanhVien";
        List<ThanhVien> list = getdata(dl);
        return list;
    }

    public ThanhVien getId(String id) {
        String dl = "SELECT * FROM ThanhVien WHERE maTV==? ";
        List<ThanhVien> list = getdata(dl, id);
        return list.get(0);
    }

    private List<ThanhVien> getdata(String dl, String... Arays) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setIDTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ThanhVien.COL_NAME_ID))));
            thanhVien.setHoTenTV(cursor.getString(cursor.getColumnIndex(ThanhVien.COL_NAME_HO_TEN)));
            thanhVien.setNamsinhTV(cursor.getString(cursor.getColumnIndex(ThanhVien.COL_NAME_NAM_SINH)));
            list.add(thanhVien);
        }
        return list;
    }
}
